package com.blog.service;

import com.blog.domain.Email;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmailService {
    void sendHtmlEmail(Email email);
}
