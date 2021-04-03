package com.task.backpac.biz.core.product.entity;

import com.task.backpac.biz.comm.entity.CommonTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends CommonTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", unique = true, nullable = false)
    private Long productId;

    @Column(length = 100, nullable = false)
    @Size(max=100, message = "{product.createfailName.msg}")
    private String productName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String productDesc;
}
