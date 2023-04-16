package com.blog.service.impl;

import com.blog.exception.BusinessException;
import com.blog.exception.Code;
import com.blog.service.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    private StringRedisTemplate stringRedisTemplate;
    @Value("redis-expire")
    private int expire;

    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, boolean isExpire) {
        if (isExpire) {
            stringRedisTemplate.opsForValue().set(key, value, expire);
        } else {
            set(key, value);
        }
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
}
