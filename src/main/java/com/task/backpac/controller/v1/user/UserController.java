package com.task.backpac.controller.v1.user;

import com.task.backpac.biz.comm.exception.BaseException;
import com.task.backpac.biz.comm.message.MulLangMessage;
import com.task.backpac.biz.comm.util.RedisUtils;
import com.task.backpac.biz.core.user.dto.UserDto;
import com.task.backpac.biz.core.user.entity.User;
import com.task.backpac.biz.core.user.service.UserService;
import com.task.backpac.config.security.JwtTokenProvider;
import com.task.backpac.controller.response.ResponseController;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"01. 회원관리"})
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@Slf4j
public class UserController{

    private final MulLangMessage lang;
    private final ResponseController response;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisUtils redisUtils;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public Object signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String userId,
                          @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {

        User user = userService.getUserByUid(userId);

        if (!passwordEncoder.matches(password, user.getUserPw())){
            throw new BaseException(lang.getMessage("user.loginFail.msg"));
        }

        return response.token(jwtTokenProvider.createToken(String.valueOf(user.getUserNo()), user.getRoles()), "", "");
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public Object signup(@Valid UserDto userDto) {
        return response.single(userService.insertUser(userDto), lang.getCode("user.authCreated.code"), lang.getMessage("user.authCreated.msg"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "로그아웃", notes = "회원 로그아웃을 수행 한다.")
    @PostMapping(value = "/signout")
    public Object signout() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        User user = userService.getUserByUid(userId);

        redisUtils.delete("JWT_TOKEN::" + user.getUserNo());

        return response.success(lang.getCode("user.logoutSuccess.code"), lang.getMessage("user.logoutSuccess.msg"));

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
    @GetMapping("/list")
    public Object getUsers() {
        return response.list(null, "", "");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "회원 이메일 정보로 회원을 조회한다")
    @GetMapping("/detail")
    public Object getUser(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String userId) {
        return response.single(userService.getUserByUid(userId), "", "");
    }
}
