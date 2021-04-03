package com.task.backpac.biz.core.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @ApiModelProperty(value = "제품 아이디", required = false)
    private Long productId;

    @ApiModelProperty(value = "제품 명(최대 100자)", required = true)
    private String productName;

    @ApiModelProperty(value = "제품 설명", required = false)
    private String productDesc;
}
