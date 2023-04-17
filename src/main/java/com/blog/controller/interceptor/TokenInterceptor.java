package com.blog.controller.interceptor;

import com.blog.service.RedisService;
import com.blog.utils.Token;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    private RedisService redisService;

    public TokenInterceptor(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("token interceptor pre handle...");
        int userId = Integer.parseInt(request.getHeader("id"));
        String token = request.getHeader("token");
        if (redisService.hasToken(userId) && redisService.getToken(userId).equals(token)) {
            redisService.resetTokenExpire(userId);
        }
        Token.verifyToken(token, request.getHeader("id"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("token interceptor post handle");
    }
}
