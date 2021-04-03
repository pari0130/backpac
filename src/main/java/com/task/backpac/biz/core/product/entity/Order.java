package com.task.backpac.biz.core.product.entity;

import com.task.backpac.biz.comm.entity.CommonTimeEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "user_order")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends CommonTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", unique = true, nullable = false)
    private Long orderNo;

    @Column(length = 12, unique = true, nullable = false)
    private String orderId;

    @Column(columnDefinition = "bigint", nullable = false)
    private Long productId;

    @Column(columnDefinition = "bigint", nullable = false)
    private Long userNo;
}
