package com.task.backpac.config.security;

import com.task.backpac.biz.comm.util.ObjectUtil;
import com.task.backpac.biz.comm.util.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider { // JWT 토큰을 생성 및 검증 모듈

    @Value("${spring.jwt.secret}")
    private String secretKey;

    private final UserDetailsService userDetailsService;
    private final RedisUtil redisUtils;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 토큰 생성
    public String createToken(String userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date now = new Date();
        long tokenValidMilisecond = 1000L * 60 * 60 * 24; // 24시간

        String token = Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(now) // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + tokenValidMilisecond)) // expire
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        this.putTokenToRedis(userPk, token, tokenValidMilisecond);

        return token;
    }

    // 토큰으로 인증정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Jwt 토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Header에서 token 파싱 X-AUTH-TOKEN
    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("X-AUTH-TOKEN");
    }

    // Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // jwt 토큰 만료 체크를 위한 redis 저장
    public void putTokenToRedis(String userPk, String token, long tokenValidMilisecond){
        Map<String, Object> cacheMap = new HashMap<>();
        cacheMap.put("userId", userPk);
        cacheMap.put("token", token);

        redisUtils.put("JWT_TOKEN::" + userPk, cacheMap, tokenValidMilisecond);

        Map<String, Object> redisMap = ObjectUtil.convertObjToMap(redisUtils.get("JWT_TOKEN::" + userPk));

        if(redisMap != null){
            log.info("redis userId : " + redisMap.get("userId"));
            log.info("redis token : " + redisMap.get("token"));
        }
    }

    public boolean validateTokenFromRedis(String token){
        String userPk = this.getUserPk(token);

        Map<String, Object> redisMap = ObjectUtil.convertObjToMap(redisUtils.get("JWT_TOKEN::" + userPk));

        if(redisMap != null && token.equals(MapUtils.getString(redisMap, "token"))){
            log.info("redis userId : " + redisMap.get("userId"));
            log.info("redis token : " + redisMap.get("token"));
            return true;
        }

        return false;
    }
}

