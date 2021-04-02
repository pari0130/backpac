package com.task.backpac.biz.core.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @ApiModelProperty(value = "회원 이메일(영문 대소문자 100자)", required = true)
    private String userEmail;

    @ApiModelProperty(value = "회원 이름(한글 10자, 영문 대소문자 20자)", required = true)
    private String userName;

    @ApiModelProperty(value = "회원 별명(영문 소문자 30자)", required = true)
    private String userNic;

    @ApiModelProperty(value = "회원 비밀번호(영문 대문자, 소문자, 특수문자, 숫자 각1자 이상 최소 10자 이상)", required = true)
    private String userPw;

    @ApiModelProperty(value = "회원 전화번호(숫자 20자)", required = true)
    private String userPhone;

    @ApiModelProperty(value = "회원 성별(남자:M, 여자:F)", required = false)
    private String userGender;
}
