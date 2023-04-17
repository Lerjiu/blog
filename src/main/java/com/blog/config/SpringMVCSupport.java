package com.blog.config;

import com.blog.controller.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCSupport implements WebMvcConfigurer {
    private TokenInterceptor tokenInterceptor;

    public SpringMVCSupport(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor).addPathPatterns("/api/**");
        registry.addInterceptor(tokenInterceptor).excludePathPatterns("/api/user/login", "/api/user/register");
    }
}
