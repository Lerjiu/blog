package com.blog.controller.interceptor;

import com.blog.utils.Token;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Token.verifyToken(request.getHeader("token"), request.getHeader("id"));
        return true;
    }
}
