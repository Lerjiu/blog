package com.blog.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RedisService {
    void set(String key, String value);
    void set(String key, String value, int expire);
    String get(String key);
    void del(String key);
    boolean has(String key);
    void expire(String key, int expire);
    void hset(String key, String field, String value);
    String hget(String key, String field);
    void setVerifyCode(String email, String verifyCode);
    String getVerifyCode(String email);
    void delVerifyCode(String email);
    void setToken(int userId, String token);
    String getToken(int userId);
    void delToken(int userId);
    boolean hasToken(int userId);
    void resetTokenExpire(int userId);

}
