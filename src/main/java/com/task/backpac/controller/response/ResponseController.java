package com.task.backpac.controller.response;

import com.mysql.cj.util.StringUtils;
import com.task.backpac.biz.comm.message.Message;
import com.task.backpac.biz.comm.message.MulLangMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ResponseController {

    private final MulLangMessage lang;

    /**
     * @Purpose : 공통 성공 메세지 처리
     * @Method Name : setSuccessMessage
     * @Author : 조동휘
     * @Date : 2021-04-02
     * @Return : void
     * @Description :
     */
    private void setSuccessMessage(Message message, boolean success, String code, String msg) {
        if(StringUtils.isNullOrEmpty(code)){
            code = lang.getCode("common.success.code");
        }
        if(StringUtils.isNullOrEmpty(msg)){
            msg = lang.getMessage("common.success.msg");
        }
        message.setSuccess(success);
        message.setCode(code);
        message.setMsg(msg);
    }

    /**
     * @Purpose : 공통 실패 메세지 처리
     * @Method Name : setFailMessage
     * @Author : 조동휘
     * @Date : 2021-04-02
     * @Return : void
     * @Description :
     */
    private void setFailMessage(Message message, boolean success, String code, String msg) {
        if(StringUtils.isNullOrEmpty(code)){
            code = lang.getCode("common.fail.code");
        }
        if(StringUtils.isNullOrEmpty(msg)){
            msg = lang.getMessage("common.fail.msg");
        }
        message.setSuccess(success);
        message.setCode(code);
        message.setMsg(msg);
    }

    /**
     * @Purpose : 성공 처리 응답
     * @Method Name : success
     * @Author : 조동휘
     * @Date : 2021-04-02
     * @Return : ResponseEntity
     * @Description
     */
    public ResponseEntity<Object> success(String code, String msg){
        Message message = new Message();
        setSuccessMessage(message, true, code, msg);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * @Purpose : 실패 처리 응답
     * @Method Name : fal
     * @Author : 조동휘
     * @Date : 2021-04-02
     * @Return : ResponseEntity
     * @Description
     */
    public ResponseEntity<Object> fail(String code, String msg){
        Message message = new Message();
        setFailMessage(message, false, code, msg);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * @Purpose : 페이지 결과 응답
     * @Method Name : page
     * @Author : 조동휘
     * @Date : 2021-04-02
     * @Return : ResponseEntity
     * @Description
     */
    public <T> ResponseEntity<Object> page(Page<T> pageList, String code, String msg){
        Message message = new Message();
        message.setData(pageList);
        setSuccessMessage(message, true, code, msg);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * @Purpose : 다중 리스트 결과 응답
     * @Method Name : list
     * @Author : 조동휘
     * @Date : 2021-04-002
     * @Return : ResponseEntity
     * @Description
     */
    public <T> ResponseEntity<Object> list(List<T> list, String code, String msg){
        Message message = new Message();
        message.setData(list);
        setSuccessMessage(message, true, code, msg);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * @Purpose : 단일 결과 응답
     * @Method Name : single
     * @Author : 조동휘
     * @Date : 2021-04-02
     * @Return : ResponseEntity
     * @Description
     */
    public <T> ResponseEntity<Object> single(T singleData, String code, String msg){
        Message message = new Message();
        message.setData(singleData);
        setSuccessMessage(message, true, code, msg);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * @Purpose : 로그인 후 토큰 결과 응답
     * @Method Name : token
     * @Author : 조동휘
     * @Date : 2021-04-02
     * @Return : ResponseEntity
     * @Description
     */
    public <T> ResponseEntity<Object> token(T token, String code, String msg){
        Message message = new Message();
        setSuccessMessage(message, true, code, msg);
        message.setData(token);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
