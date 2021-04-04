package com.task.backpac.biz.comm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ObjectUtil {

    /**
     * @Purpose : Dto 를 object로 받아서 Map 형태로 변환
     * @Method Name    : convertObjToMap
     * @Author : 조동휘
     * @Date : 2021-04-02
     * @Return : Map<String, Object>
     * @Description :
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> convertObjToMap(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> paramMap = new HashMap<>();
            if (obj != null) {
                paramMap = objectMapper.convertValue(obj, Map.class);
            }
            return paramMap;
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return null;
    }
}
