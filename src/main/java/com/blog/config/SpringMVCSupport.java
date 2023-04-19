package com.blog.config;

import com.blog.controller.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCSupport implements WebMvcConfigurer {
    @Value("${file.path}")
    private String path;
    @Value("${file.location}")
    private String location;
    private String[] noTokenInterceptor = {"/api/user/login", "/api/user/register", "/api/user/getVerifyCode"};
    private TokenInterceptor tokenInterceptor;

    public SpringMVCSupport(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(path).addResourceLocations("file:" + location);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/api/**").excludePathPatterns(noTokenInterceptor);
    }

}
