package com.blog.service;

public interface RedisService {
    void set(String key, String value);
    void set(String key, String value, boolean isExpire);
    String get(String key);
    void del(String key);
    void hset(String key, String field, String value);
    String hget(String key, String field);

}
