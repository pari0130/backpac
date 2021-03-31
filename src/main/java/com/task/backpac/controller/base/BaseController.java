package com.task.backpac.controller.base;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BaseController {

    public ResponseEntity<Object> success(int code, String msg){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Object> fail(int code, String msg){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> resPage(Page<T> list){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> resList(List<T> list){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> resSingle(T data){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> resToken(T token){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
