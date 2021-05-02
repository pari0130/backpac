package com.task.backpac.config.web;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods(
                        "GET",
                        "HEAD",
                        "POST",
                        "PUT",
                        "DELETE",
                        "PATCH"
                )
                .allowCredentials(false).maxAge(3600);
    }
}