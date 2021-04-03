package com.task.backpac.biz.core.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class OrderDto {

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Req {

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String orderId;

        @ApiModelProperty(value = "제품 번호")
        private Long productId;

        @ApiModelProperty(value = "주문 회원의 사용자 번호")
        private Long userNo;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Res {

        @ApiModelProperty(value = "주문 번호")
        private String orderId;

        @ApiModelProperty(value = "주문 회원의 사용자 번호")
        private Long userNo;

        @ApiModelProperty(value = "제품 이름")
        private String productName;

        @ApiModelProperty(value = "제품 상세정보")
        private String productDesc;

        @ApiModelProperty(value = "주문 일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime orderDate;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserOrderRes {

        @ApiModelProperty(value = "주문 회원의 사용자 번호")
        private Long userNo;

        @ApiModelProperty(value = "주문 회원의 사용자 이메일")
        private String userEmail;

        @ApiModelProperty(value = "주문 회원의 사용자 이름")
        private String userName;

        @ApiModelProperty(value = "마지막 주문 번호")
        private String lastOrderId;

        @ApiModelProperty(value = "제품 이름")
        private String productName;

        @ApiModelProperty(value = "제품 상세정보")
        private String productDesc;

        @ApiModelProperty(value = "주문 일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime orderDate;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserOrderReq {

        @ApiModelProperty(value = "주문 회원의 사용자 번호")
        private Long userNo;

        @ApiModelProperty(value = "주문 회원의 사용자 이름")
        private String userName;

        @ApiModelProperty(value = "주문 회원의 사용자 이메일")
        private String userEmail;

        @ApiModelProperty(value = "각 회원의 마지막 주문 정보 여부")
        private boolean isLastOrder = false;

        @ApiModelProperty(value = "주문일자 시작(ex:2021-01-01 09:00:00)")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime searchStartDate;

        @ApiModelProperty(value = "주문일자 종료(ex:2021-01-01 09:00:00)")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime searchEndDate;
    }
}
