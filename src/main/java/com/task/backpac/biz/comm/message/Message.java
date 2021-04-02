package com.task.backpac.biz.comm.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Message {

    @ApiModelProperty(value = "성공여부")
    private boolean success = true;

    @ApiModelProperty(value = "응답 코드 번호")
    private String code;

    @ApiModelProperty(value = "응답 메시지")
    private String msg;

    @ApiModelProperty(value = "응답 결과 body")
    private Object data;
}
