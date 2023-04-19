package com.blog.exception;

public class Code {
    public static final int SYSTEM_EXCEPTION = 500001;
    public static final String SYSTEM_EXCEPTION_MESSAGE = "system error...";
    public static final int BUSINESS_EXCEPTION = 600001;
    public static final String BUSINESS_EXCEPTION_MESSAGE = "business error...";
    public static final int UNKNOWN_EXCEPTION = 999999;
    public static final String UNKNOWN_EXCEPTION_MESSAGE = "unknown error...";

    // TODO: 2023/4/15 添加各种业务操作对应的code
    public static final int TOKEN_INVALID = 10001;
    public static final String TOKEN_INVALID_MESSAGE = "token expired time...";

    public static final int TOKEN_INVALID_USER = 10002;
    public static final String TOKEN_INVALID_USER_MESSAGE = "token invalid user...";

    public static final int EMAIL_SEND_EXCEPTION = 20001;
    public static final String EMAIL_SEND_EXCEPTION_MESSAGE = "email send error...";
    public static final int EMAIL_SEND_VERIFY_CODE = 20000;
    public static final String EMAIL_SEND_VERIFY_CODE_MESSAGE = "send email verify code success...";
    public static final int EMAIL_VERIFY_CODE_INVALID = 20002;
    public static final String EMAIL_VERIFY_CODE_INVALID_MESSAGE = "email verify code invalid...";
    public static final int EMAIL_VERIFY_CODE_ERROR = 20003;
    public static final String EMAIL_VERIFY_CODE_ERROR_MESSAGE = "email verify code error...";

    public static final int REDIS_INVALID_KEY = 30001;
    public static final String REDIS_INVALID_KEY_MESSAGE = "redis invalid key...";

    public static final int USER_REGISTER = 40000;
    public static final String USER_REGISTER_MESSAGE = "user register success...";
    public static final int USER_LOGIN = 40010;
    public static final String USER_LOGIN_MESSAGE = "user login success...";
    public static final int USER_UPDATE_INFO = 40020;
    public static final String USER_UPDATE_INFO_MESSAGE = "user update info success...";
    public static final int USER_UPDATE_AVATAR = 40030;
    public static final String USER_UPDATE_AVATAR_MESSAGE = "user update avatar success...";
    public static final int USER_UPDATE_PASSWORD = 40040;
    public static final String USER_UPDATE_PASSWORD_MESSAGE = "user update password success...";
    public static final int USER_PASSWORD_ERROR = 40041;
    public static final String USER_PASSWORD_ERROR_MESSAGE = "user password error...";
    public static final int USER_GET_INFO = 40050;
    public static final String USER_GET_INFO_MESSAGE = "user get info success...";

    public static final int FILE_SAVE_EXCEPTION = 50001;
    public static final String FILE_SAVE_EXCEPTION_MESSAGE = "file save error...";
    public static final int FILE_EMPTY_EXCEPTION = 50002;
    public static final String FILE_EMPTY_EXCEPTION_MESSAGE = "file empty error...";
}
