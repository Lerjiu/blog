package com.blog.config;

import com.blog.controller.interceptor.PermissionInterceptor;
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
    private String[] noTokenInterceptor = {"/api/user/login", "/api/user/register", "/api/user/getUserNames", "/api/article/get**", "/api/label/get**", "/api/comment/get**"};
    private TokenInterceptor tokenInterceptor;
    private PermissionInterceptor permissionInterceptor;

    public SpringMVCSupport(TokenInterceptor tokenInterceptor, PermissionInterceptor permissionInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
        this.permissionInterceptor = permissionInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(path).addResourceLocations("file:" + location);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/api/**").excludePathPatterns(noTokenInterceptor);
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/api/*/update", "/api/*/delete");
    }

}
