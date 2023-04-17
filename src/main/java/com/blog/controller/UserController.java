package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.controller.response.data.UserToken;
import com.blog.domain.User;
import com.blog.exception.Code;
import com.blog.service.EmailVerifyService;
import com.blog.service.RedisService;
import com.blog.service.UserService;
import com.blog.utils.Token;
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
        String redisVerifyCode = redisService.getVerifyCode(user.getEmail());
        if (redisVerifyCode == null) {
            return Response.fail(Code.EMAIL_VERIFY_CODE_INVALID, Code.EMAIL_VERIFY_CODE_INVALID_MESSAGE);
        } else if (!redisVerifyCode.equals(verifyCode)) {
            return Response.fail(Code.EMAIL_VERIFY_CODE_ERROR, Code.EMAIL_VERIFY_CODE_ERROR_MESSAGE);
        } else {
            redisService.delVerifyCode(user.getEmail());
            userService.register(user);
            return Response.success(Code.USER_REGISTER, Code.USER_REGISTER_MESSAGE);
        }
    }

    @RequestMapping("/getVerifyCode")
    public Response getVerifyCode(String email) {
        emailVerifyService.sendVerifyEmail(email, "您正在尝试进行登录操作，若非是您本人的行为，请忽略!");
        return Response.success(Code.EMAIL_SEND_VERIFY_CODE, Code.EMAIL_SEND_VERIFY_CODE_MESSAGE);
    }

    @RequestMapping("/login")
    public DataResponse login(String email, String password) {
        int userId = userService.getUserIdByEmailAndPassword(email, password);
        String token = null;
        if (redisService.hasToken(userId)) {
            token = redisService.getToken(userId);
        } else {
            token = Token.createToken(userId);
            redisService.setToken(userId, token);
        }
        return DataResponse.success(Code.USER_LOGIN, Code.USER_LOGIN_MESSAGE, new UserToken(userId, token));
    }

}
