package com.blog.service;

import com.blog.domain.Email;

public interface EmailService {
    void sendHtmlEmail(Email email);
}
