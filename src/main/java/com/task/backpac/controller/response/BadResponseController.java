package com.task.backpac.controller.response;

import com.task.backpac.biz.comm.exception.BaseException;
import com.task.backpac.biz.comm.message.MulLangMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@ControllerAdvice
@RestController
public class BadResponseController {

    private final MulLangMessage lang;
    private final ResponseController response;

    /**
     * @Purpose : 유효성 검사 실패에 대한 응답 처리
     * @Method Name : validException
     * @Author : 조동휘
     * @Date : 2021-04-02
     * @Return : Object
     * @Description :
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Object validException(MethodArgumentNotValidException ex) {
        return response.fail(lang.getCode("common.validate.code"), "유효성 검사 실패 : " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * @Purpose : 공통 BaseException 처리
     * @Method Name : baseException
     * @Author : 조동휘
     * @Date : 2021-04-02
     * @Return : Object
     * @Description :
     */
    @ExceptionHandler({BaseException.class})
    public Object baseException(BaseException ex) {

        return response.fail(lang.getCode("common.validate.code"), ex.getMessage());
    }
}
