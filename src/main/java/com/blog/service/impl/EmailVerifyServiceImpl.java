package com.blog.service.impl;

import com.blog.domain.Email;
import com.blog.service.EmailService;
import com.blog.service.EmailVerifyService;
import com.blog.service.RedisService;
import com.blog.utils.EmailVerify;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailVerifyServiceImpl implements EmailVerifyService {
    private EmailService emailService;
    private TemplateEngine templateEngine;
    private RedisService redisService;

    public EmailVerifyServiceImpl(EmailService emailService, TemplateEngine templateEngine, RedisService redisService) {
        this.emailService = emailService;
        this.templateEngine = templateEngine;
        this.redisService = redisService;
    }

    @Override
    public void sendVerifyEmail(String to, String message) {
        String code = EmailVerify.randomCode();
        redisService.setVerifyCode(to, code);
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("code", code);
        String content = templateEngine.process("emailtemplate.html", context);
        emailService.sendHtmlEmail(new Email(to, "邮箱验证", content));
    }
}
