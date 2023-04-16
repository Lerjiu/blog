package com.blog.service;

import com.blog.domain.Email;

public interface EmailVerifyService {
    void sendVerifyEmail(String to, String message);
}
