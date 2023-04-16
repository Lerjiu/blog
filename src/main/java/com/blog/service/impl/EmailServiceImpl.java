package com.blog.service.impl;

import com.blog.domain.Email;
import com.blog.exception.Code;
import com.blog.exception.SystemException;
import com.blog.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String fromEmail;
    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendHtmlEmail(Email email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(email.getTo());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getContent(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new SystemException(Code.EMAIL_SEND_EXCEPTION, Code.EMAIL_SEND_EXCEPTION_MESSAGE, e);
        }

    }
}
