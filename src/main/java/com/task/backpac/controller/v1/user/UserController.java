package com.task.backpac.controller.v1.user;

import com.task.backpac.biz.comm.message.MulLangMessage;
import com.task.backpac.biz.core.user.dto.UserDto;
import com.task.backpac.biz.core.user.entity.User;
import com.task.backpac.biz.core.user.service.UserService;
import com.task.backpac.config.security.JwtTokenProvider;
import com.task.backpac.controller.response.ResponseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public Object signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
                          @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {

        User user = null;
//                userJpaRepo.findByUid(id).orElseThrow(CEmailSigninFailedException::new);
//
//        if (!passwordEncoder.matches(password, user.getUserPw()))
//            throw new CEmailSigninFailedException();

        return response.single(jwtTokenProvider.createToken(String.valueOf(user.getUserNo()), user.getRoles()), "", "");
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public Object signup(UserDto userDto) {
        return response.single(userService.insertUser(userDto), lang.getCode("user.authCreated.code"), lang.getMessage("user.authCreated.code"));
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
    @GetMapping("/users")
    public Object getUsers() {
        return response.list(null, "", "");
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
    @ApiOperation(value = "회원 단건 조회", notes = "회원번호로 회원을 조회한다")
    @GetMapping("/user")
    public Object getUser() {
        return response.single(null, "", "");
    }
}
