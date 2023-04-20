package com.blog.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmailVerifyService {
    void sendVerifyEmail(String to, String message);
}
