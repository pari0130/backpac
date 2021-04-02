package com.task.backpac.biz.core.user.repo;

import com.task.backpac.biz.core.user.dto.UserDto;
import com.task.backpac.biz.core.user.entity.User;

import java.util.Optional;

public interface UserRepository {

    User insertUser(UserDto userDto);

    User getUserByUid(String email);

    Optional<User> getUserByUserNo(Long userNo);
}
