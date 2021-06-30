package com.task.backpac.biz.core.user.service;

import com.task.backpac.biz.core.user.dto.UserDto;
import com.task.backpac.biz.core.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    /**
     * @Purpose : 스프링 시큐리티 user name 조회
     * @Method Name : loadUserByUsername
     * @Author : 조동휘
     * @Date : 2021-04-03
     * @param userNo : 조회 하는 사용자 번호
     * @Return : UserDetails
     * @Description
     */
    UserDetails loadUserByUsername(String userNo);

    /**
     * @Purpose : 사용자 회원 가입
     * @Method Name : insertUser
     * @Author : 조동휘
     * @Date : 2021-04-03
     * @param userDto : 가입정보 dto
     * @Return : User
     * @Description
     */
    User insertUser(UserDto.Signin userDto);

    /**
     * @Purpose : 사용자 이메일 정보로 상세 조회
     * @Method Name : getUserByUid
     * @Author : 조동휘
     * @Date : 2021-04-03
     * @param email : 사용자 이메일 정보
     * @Return : String
     * @Description
     */
    User getUserByUid(String email);

    /**
     * @Purpose : 스프링 시큐리티 user name 조회
     * @Method Name : getUserByUserNo
     * @Author : 조동휘
     * @Date : 2021-04-03
     * @param userNo : 조회 하는 사용자 번호
     * @Return : UserDetails
     * @Description
     * @return
     */
    User getUserByUserNo(String userNo);

    /**
     * @Purpose : 저장된 모든 사용자 정보 조회
     * @Method Name : selectUsers
     * @Author : 조동휘
     * @Date : 2021-04-03
     * @Return : Page<UserDto.Res>
     * @Description
     */
    Page<UserDto.Res> selectUsers(Pageable pageable);

    /**
     * @Purpose : 사용자 이메일 정보 카운트
     * @Method Name : getEmailCount
     * @Author : 조동휘
     * @Date : 2021-06-30
     * @param email : 사용자 이메일 정보
     * @Return : String
     * @Description
     */
    Integer getEmailCount(String email);

    /**
     * @Purpose : 사용자 이메일 아이디 검색
     * @Method Name : getUserEmail
     * @Author : 조동휘
     * @Date : 2021-06-30
     * @param phone : 사용자 이메일 정보
     * @Return : String
     * @Description
     */
    String getUserEmail(String phone);

    String resetPassword(String email);

    Boolean updatePassword(String email, String password);
}
