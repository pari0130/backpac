package com.task.backpac.biz.core.user.service;

import com.task.backpac.biz.core.user.dto.UserDto;
import com.task.backpac.biz.core.user.entity.User;

public interface UserService {

    User insertUser(UserDto userDto);
}
