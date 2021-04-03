package com.task.backpac.biz.core.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
public class UserDto {

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Signin {

        @ApiModelProperty(value = "회원 이메일(영문 대소문자 100자)", required = true)
        @Email(message = "{user.authFailEmail.msg}")
        @Size(max=100, message = "{common.size.msg}")
        private String userEmail;

        @ApiModelProperty(value = "회원 이름(한글, 영문 대소문자 20자)", required = true)
        @Pattern(regexp="^[a-zA-Z가-힣]{1,20}$", message = "{user.authFailName.msg}")
        private String userName;

        @ApiModelProperty(value = "회원 별명(영문 소문자 30자)", required = true)
        @Pattern(regexp="^[a-zA-Z]{1,30}$", message = "{user.authFailNik.msg}")
        private String userNic;

        @ApiModelProperty(value = "회원 비밀번호(영문 대문자, 소문자, 특수문자, 숫자 각1자 이상 최소 10자 이상)", required = true)
        @Pattern(regexp="^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{10,}$", message = "{user.authFailPw.msg}")
        private String userPw;

        @ApiModelProperty(value = "회원 전화번호(숫자 20자)", required = true)
        @Pattern(regexp="^[0-9]{9,20}$", message = "{user.authFailPhone.msg}")
        private String userPhone;

        @ApiModelProperty(value = "회원 성별(남자:m, 여자:f)", required = false)
        @Pattern(regexp="[mf]", message = "{user.authFailGender.msg}")
        private String userGender;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Res {

        @ApiModelProperty(value = "회원 이메일", required = true)
        private String userEmail;

        @ApiModelProperty(value = "회원 이름", required = true)
        private String userName;

        @ApiModelProperty(value = "회원 별명", required = true)
        private String userNic;

        @ApiModelProperty(value = "회원 전화번호", required = true)
        private String userPhone;

        @ApiModelProperty(value = "회원 성별", required = false)
        private String userGender;
    }
}
