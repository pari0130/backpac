package com.task.backpac.biz.core.user.repo;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.task.backpac.biz.core.user.dto.UserDto;
import com.task.backpac.biz.core.user.entity.QUser;
import com.task.backpac.biz.core.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepository {

    private final EntityManager entityManager;
    private final UserJPARepository userJPARepository;
    private final JPAQueryFactory queryFactory;
    private final PasswordEncoder passwordEncoder;

    public UserRepositoryImpl(EntityManager entityManager, UserJPARepository userJPARepository, JPAQueryFactory queryFactory, PasswordEncoder passwordEncoder) {
        super(User.class);
        this.entityManager = entityManager;
        this.userJPARepository = userJPARepository;
        this.queryFactory = queryFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User insertUser(UserDto.Signin userDto) {

        User user = User.builder()
                .userEmail(userDto.getUserEmail())
                .userPw(passwordEncoder.encode(userDto.getUserPw()))
                .userName(userDto.getUserName())
                .userNic(userDto.getUserNic())
                .userPhone(userDto.getUserPhone())
                .userGender(userDto.getUserGender())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        return userJPARepository.save(user);
    }

    @Override
    public User getUserByUid(String email) {
        QUser user = QUser.user;

        JPAQuery<User> query = queryFactory.selectFrom(user)
                .where(user.userEmail.eq(email));

        return query.fetchOne();
    }

    @Override
    public Optional<User> getUserByUserNo(Long userNo) {
        return userJPARepository.findById(userNo);
    }

    @Override
    public Page<UserDto.Res> selectUsers(Pageable pageable) {

        QUser qUser = QUser.user;

        JPAQuery<UserDto.Res> query = new JPAQuery<UserDto.Res>(entityManager);

        query.from(qUser);

        List<UserDto.Res> paging = getQuerydsl().applyPagination(pageable, query).fetch();

        return new PageImpl<>(paging, pageable, query.fetchCount());
    }
}
