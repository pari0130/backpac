package com.task.backpac.controller.response;

import com.mysql.cj.util.StringUtils;
import com.task.backpac.biz.comm.message.Message;
import com.task.backpac.biz.comm.message.MulLangMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResponseController {

    private final MulLangMessage lang;

    private void setMessage(Message message, boolean success, String code, String msg) {

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

    public ResponseEntity<Object> success(String code, String msg){
        Message message = new Message();
        setMessage(message, true, code, msg);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public ResponseEntity<Object> fail(String code, String msg){
        Message message = new Message();
        setMessage(message, false, code, msg);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> page(Page<T> pageList, String code, String msg){
        Message message = new Message();
        message.setData(pageList);
        setMessage(message, true, code, msg);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> list(List<T> list, String code, String msg){
        Message message = new Message();
        message.setData(list);
        setMessage(message, true, code, msg);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> single(T singleData, String code, String msg){
        Message message = new Message();
        message.setData(singleData);
        setMessage(message, true, code, msg);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> token(T token, String code, String msg){
        Message message = new Message();
        setMessage(message, true, code, msg);
        message.setData(token);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
