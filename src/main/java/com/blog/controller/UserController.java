package com.blog.controller;

import com.blog.controller.response.Response;
import com.blog.domain.User;
import com.blog.exception.Code;
import com.blog.service.EmailVerifyService;
import com.blog.service.RedisService;
import com.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/user")
@ResponseBody
public class UserController {
    private EmailVerifyService emailVerifyService;
    private RedisService redisService;
    private UserService userService;

    public UserController(EmailVerifyService emailVerifyService, RedisService redisService, UserService userService) {
        this.emailVerifyService = emailVerifyService;
        this.redisService = redisService;
        this.userService = userService;
    }

    @RequestMapping("/register")
    public Response register(User user, String verifyCode) {
        String redisVerifyCode = redisService.get(user.getEmail());
        if (redisVerifyCode == null) {
            return Response.fail(Code.EMAIL_VERIFY_CODE_INVALID, Code.EMAIL_VERIFY_CODE_INVALID_MESSAGE);
        } else if (!redisVerifyCode.equals(verifyCode)) {
            return Response.fail(Code.EMAIL_VERIFY_CODE_ERROR);
        } else {
            redisService.del(user.getEmail());
            userService.register(user);
            return Response.success(Code.USER_REGISTER, Code.USER_REGISTER_MESSAGE);
        }
    }

    @RequestMapping("/getVerifyCode")
    public Response getVerifyCode(String email) {
        emailVerifyService.sendVerifyEmail(email, "您正在尝试进行登录操作，若非是您本人的行为，请忽略!");
        return Response.success(Code.EMAIL_SEND_VERIFY_CODE, Code.EMAIL_SEND_VERIFY_CODE_MESSAGE);
    }
}
