package com.task.backpac.biz.core.user.repo;

import com.task.backpac.biz.core.user.dto.UserDto;
import com.task.backpac.biz.core.user.entity.User;

public interface UserRepository {

    User insertUser(UserDto userDto);

}
