package com.task.backpac.biz.comm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    public void put(String key, Object value, Long expirationTime){
        if(expirationTime != null){
            redisTemplate.opsForValue().set(key, value, expirationTime, TimeUnit.SECONDS);
        }else{
            redisTemplate.opsForValue().set(key, value);
        }
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    public Object get(String key) {
        Object result = null;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    public boolean isExists(String key){
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void setExpireTime(String key, long expirationTime){
        redisTemplate.expire(key, expirationTime, TimeUnit.SECONDS);
    }

    public long getExpireTime(String key){
        if(Boolean.TRUE.equals(redisTemplate.hasKey(key))){
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        }
        return 0;
    }
}


