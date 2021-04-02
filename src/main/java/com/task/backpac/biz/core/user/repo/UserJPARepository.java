package com.task.backpac.biz.core.user.repo;

import com.task.backpac.biz.core.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User, Long> {
}
