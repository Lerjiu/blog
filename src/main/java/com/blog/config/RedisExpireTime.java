package com.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("redis-expire-time")
public class RedisExpireTime {
    private int verifycode;
    private int token;

    public int getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(int verifycode) {
        this.verifycode = verifycode;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}
