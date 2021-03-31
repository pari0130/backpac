package com.task.backpac.biz.core.product.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
public class Order {
    /*
    주문번호 12 중복이 불가능한 임의의 영문 대문자, 숫자 조합
    제품 pk
    주문자 user pk
    결제일시  Datetime Timezone 을 고려한 시간 정보
    * */

    @Id // 해당 테이블의 PK 필드를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)// PK의 생성 규칙을 나타냅니다. 기본값은 AUTO 로, MySQL의 auto_increment와 같이 자동증가하는 정수형 값이 됩니다.
    private Long orderNo;
}
