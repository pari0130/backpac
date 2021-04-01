package com.task.backpac.controller.response;

import com.task.backpac.biz.comm.message.Message;
import com.task.backpac.biz.comm.message.MulLangMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
public class ResponseController {

    private final MulLangMessage langMessage;

    private void setMessage(Message message, boolean success, int code, String msg) {
        message.setSuccess(success);
        message.setCode(code);
        message.setMsg(msg);
    }

    public ResponseEntity<Object> success(int code, String msg){
        Message message = new Message();
        setMessage(message, true, code, msg);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public ResponseEntity<Object> fail(int code, String msg){
        Message message = new Message();
        setMessage(message, false, code, msg);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> page(Page<T> pageList){
        Message message = new Message();
        message.setData(pageList);
        setMessage(message, true, langMessage.getCode("common.success.code"), langMessage.getMessage("common.success.msg"));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> list(List<T> list){
        Message message = new Message();
        message.setData(list);
        setMessage(message, true, langMessage.getCode("common.success.code"), langMessage.getMessage("common.success.msg"));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> single(T singleData){
        Message message = new Message();
        message.setData(singleData);
        setMessage(message, true, langMessage.getCode("common.success.code"), langMessage.getMessage("common.success.msg"));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> token(T token){
        Message message = new Message();
        setMessage(message, true, langMessage.getCode("common.success.code"), langMessage.getMessage("common.success.msg"));
        message.setData(token);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}