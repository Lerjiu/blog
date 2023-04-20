package com.blog.service.impl;

import com.blog.config.RedisExpireTime;
import com.blog.service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    private static final TimeUnit REDIS_TIME_UNIT = TimeUnit.SECONDS;
    private StringRedisTemplate stringRedisTemplate;
    private RedisExpireTime redisExpireTime;

    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate, RedisExpireTime redisExpireTime) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisExpireTime = redisExpireTime;
    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, int expire) {
        stringRedisTemplate.opsForValue().set(key, value, expire, REDIS_TIME_UNIT);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public boolean has(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public void expire(String key, int expire) {
        stringRedisTemplate.expire(key, expire, REDIS_TIME_UNIT);
    }

    @Override
    public void hset(String key, String field, String value) {
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        Object result = stringRedisTemplate.opsForHash().get(key, field);
        if (result == null) {
            return null;
        }
        return result.toString();
    }

    @Override
    public void setVerifyCode(String email, String verifyCode) {
        set("verify_code:" + email, verifyCode, redisExpireTime.getVerifycode());
    }

    @Override
    public String getVerifyCode(String email) {
        return get("verify_code:" + email);
    }

    @Override
    public void delVerifyCode(String email) {
        del("verify_code:" + email);
    }

    @Override
    public void setToken(int userId, String token) {
        set("token:" + userId, token, redisExpireTime.getToken());
    }

    @Override
    public String getToken(int userId) {
        return get("token:" + userId);
    }

    @Override
    public void delToken(int userId) {
        del("token:" + userId);
    }

    @Override
    public boolean hasToken(int userId) {
        return has("token:" + userId);
    }

    @Override
    public void resetTokenExpire(int userId) {
        expire("token:" + userId, redisExpireTime.getToken());
    }
}
