package com.blog.controller;

import com.blog.controller.response.DataResponse;
import com.blog.controller.response.Response;
import com.blog.controller.response.data.UserInfo;
import com.blog.controller.response.data.UserToken;
import com.blog.domain.FavoriteFolder;
import com.blog.domain.User;
import com.blog.exception.Code;
import com.blog.service.*;
import com.blog.utils.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Controller
@RequestMapping("/api/user")
@ResponseBody
public class UserController {
    private EmailVerifyService emailVerifyService;
    private RedisService redisService;
    private UserService userService;
    private FileService fileService;
    private FavoriteFolderService collectionFolderService;
    @Value("${file.avatar}")
    private String avatarPath;

    public UserController(EmailVerifyService emailVerifyService, RedisService redisService, UserService userService, FileService fileService, FavoriteFolderService collectionFolderService) {
        this.emailVerifyService = emailVerifyService;
        this.redisService = redisService;
        this.userService = userService;
        this.fileService = fileService;
        this.collectionFolderService = collectionFolderService;
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
            FavoriteFolder collectionFolder = new FavoriteFolder();
            collectionFolder.setUserId(user.getId());
            collectionFolder.setName("默认收藏夹");
            collectionFolderService.add(collectionFolder);
            userService.addDefaultFavoriteFolder(user.getId(), collectionFolder.getId());
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

    @RequestMapping("/logout")
    public Response logout(@RequestHeader("id") int id) {
        redisService.delToken(id);
        return Response.success(Code.USER_LOGOUT, Code.USER_LOGOUT_MESSAGE);
    }

    @RequestMapping("getInfo")
    public DataResponse getInfo(@RequestHeader("id") int id) {
        User user = userService.getUserById(id);
        return DataResponse.success(Code.USER_GET_INFO, Code.USER_GET_INFO_MESSAGE, UserInfo.getUserInfoFromUser(user));
    }

    @RequestMapping("getOtherInfo")
    public DataResponse getOtherInfo(int id) {
        User user = userService.getUserById(id);
        return DataResponse.success(Code.USER_GET_INFO, Code.USER_GET_INFO_MESSAGE, UserInfo.getUserInfoFromUser(user));
    }

    @RequestMapping("/updateInfo")
    public Response updateInfo(@RequestHeader("id") int id, String name, @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday, String description, boolean sex) {
        userService.updateInfo(id, name, birthday, description, sex);
        return Response.success(Code.USER_UPDATE_INFO, Code.USER_UPDATE_INFO_MESSAGE);
    }

    @RequestMapping("/updateAvatar")
    public Response updateAvatar(@RequestHeader("id") int id, MultipartFile avatar) {
        String avatarName = fileService.upload(avatar, avatarPath);
        userService.updateAvatar(id, avatarName);
        return Response.success(Code.USER_UPDATE_AVATAR, Code.USER_UPDATE_AVATAR_MESSAGE);
    }

    @RequestMapping("/updatePassword")
    public Response updatePassword(@RequestHeader("id") int id, String oldPassword, String newPassword) {
        if (userService.updatePassword(id, oldPassword, newPassword)) {
            return Response.success(Code.USER_UPDATE_PASSWORD, Code.USER_UPDATE_PASSWORD_MESSAGE);
        } else {
            return Response.fail(Code.USER_PASSWORD_ERROR, Code.USER_PASSWORD_ERROR_MESSAGE);
        }
    }
}
