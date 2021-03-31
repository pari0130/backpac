package com.task.backpac;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ServletComponentScan // @WebFilter 를 사용하기 위한 어노테이션
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RequiredArgsConstructor
@SpringBootApplication
public class BackpacApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackpacApplication.class, args);
    }
}
