package com.task.backpac;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@EnableJpaAuditing // JPA Auditing 활성화
@ServletComponentScan // @WebFilter 를 사용하기 위한 어노테이션
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RequiredArgsConstructor
@SpringBootApplication
public class BackpacApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackpacApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public RestTemplate getRestTemplate() { return new RestTemplate(); }
}
