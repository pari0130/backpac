package com.task.backpac.biz.core.user.service;

import com.task.backpac.biz.core.user.dto.UserDto;
import com.task.backpac.biz.core.user.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String userNo);

    User insertUser(UserDto userDto);

    User getUserByUid(String email);
}
