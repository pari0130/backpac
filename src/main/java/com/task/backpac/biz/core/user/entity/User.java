package com.task.backpac.biz.core.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
public class User {
    /*
    user pk long 숫자증가 자동

    이름 20 한글, 영문 대소문자만 허용
    별명 30 영문 소문자만 허용
    비밀번호 최소 10자 이상 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함
    전화번호 20 숫자
    이메일 100 이메일형식
    성별 옵셔널 1
    */
    @Id // 해당 테이블의 PK 필드를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)// PK의 생성 규칙을 나타냅니다. 기본값은 AUTO 로, MySQL의 auto_increment와 같이 자동증가하는 정수형 값이 됩니다.
    private Long userNo;

    @Column(name="USER_ID", columnDefinition = "bigint",  unique = true, nullable = false)
    private Long userId;
}
