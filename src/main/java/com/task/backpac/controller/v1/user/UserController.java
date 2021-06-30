package com.task.backpac.controller.v1.user;

import com.task.backpac.biz.comm.exception.BaseException;
import com.task.backpac.biz.comm.message.MulLangMessage;
import com.task.backpac.biz.comm.util.RedisUtil;
import com.task.backpac.biz.core.user.dto.UserDto;
import com.task.backpac.biz.core.user.entity.User;
import com.task.backpac.biz.core.user.service.UserService;
import com.task.backpac.config.security.JwtTokenProvider;
import com.task.backpac.controller.response.ResponseController;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"01. 회원관리"})
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Slf4j
public class UserController {

    private final MulLangMessage lang;
    private final ResponseController response;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisUtil redisUtils;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/users/signin")
    public Object signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String userId,
                         @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {

        User user = userService.getUserByUid(userId);

        if (!passwordEncoder.matches(password, user.getUserPw())) {
            throw new BaseException(lang.getMessage("user.loginFail.msg"));
        }

        String token = jwtTokenProvider.createToken(String.valueOf(user.getUserNo()), user.getRoles());

        if(StringUtils.isNotEmpty(token)){
            UserDto.Res userDto = new UserDto.Res();
            userDto.setUserEmail(user.getUserEmail());
            userDto.setUserName(user.getUserRealName());
            userDto.setUserNic(user.getUserNic());
            userDto.setUserPhone(user.getUserPhone());
            userDto.setUserGender(user.getUserGender());
            userDto.setUserToken(token);

            return response.single(userDto, lang.getCode("0000"), lang.getMessage("성공하였습니다."));
        }else{
            return response.single("", lang.getCode("0000"), lang.getMessage("실패하였습니다."));
        }
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/users/signup")
    public Object signup(@Valid UserDto.Signin userDto) {
        int count = userService.getEmailCount(userDto.getUserEmail());

        if (count > 0) {
            return response.single(count, lang.getCode("0000"), lang.getMessage("중복된 이메일 입니다."));
        } else {
            return response.single(userService.insertUser(userDto), lang.getCode("user.authCreated.code"), lang.getMessage("user.authCreated.msg"));
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "로그아웃", notes = "회원 로그아웃을 수행 한다.")
    @PostMapping(value = "/users/signout")
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
    @GetMapping("/users")
    public Object getUsers(@ApiParam(value = "페이지 번호", required = true) @RequestParam(defaultValue = "0") int page,
                           @ApiParam(value = "페이지 로우 사이즈", required = true) @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return response.page(userService.selectUsers(pageable), "", "");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "회원 번호로 정보를 조회한다")
    @GetMapping("/users/{userNo}")
    public Object getUser(@ApiParam(value = "회원 번호", required = true) @PathVariable String userNo) {
        return response.single(userService.getUserByUserNo(userNo), "", "");
    }

    @ApiOperation(value = "중복체크", notes = "회원가입시 이메일 중복체크를 수행한다")
    @GetMapping(value = "/users/count")
    public Object emailCount(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String email) {
        int count = userService.getEmailCount(email);

        if (count > 0) {
            return response.single(count, lang.getCode("0000"), lang.getMessage("중복된 이메일 입니다."));
        } else {
            return response.single(count, lang.getCode("0000"), lang.getMessage("사용 가능한 이메일 입니다."));
        }
    }

    @ApiOperation(value = "비밀번호 초기회", notes = "비밀번호를 초기화 한다.")
    @PostMapping(value = "/users/reset")
    public Object resetPassword(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String email) {
        int count = userService.getEmailCount(email);

        if (count > 0) {
            String initPw = userService.resetPassword(email);
            if(StringUtils.isEmpty(initPw)){
                return response.single(initPw, lang.getCode("0000"), lang.getMessage("초기화에 실패 하였습니다."));
            }else{
                return response.single(initPw, lang.getCode("0000"), lang.getMessage("초기화 되었습니다."));
            }
        } else {
            return response.single(count, lang.getCode("0000"), lang.getMessage("존재하지 않는 이메일 입니다."));
        }
    }

    @ApiOperation(value = "비밀번호 변경", notes = "비밀번호를 변경한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping(value = "/users/update-password")
    public Object updatePassword(@Valid UserDto.ResetPw userDto) {
        if (userService.updatePassword(userDto.getUserEmail(), userDto.getUserPw())) {
            return response.single(true, lang.getCode("0000"), lang.getMessage("비밀번호 변경에 성공했습니다."));
        } else {
            return response.single(false, lang.getCode("0000"), lang.getMessage("비밀번호 변경에 실패했습니다."));
        }
    }
}
