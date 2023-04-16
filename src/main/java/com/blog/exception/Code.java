package com.blog.exception;

public class Code {
    public static final int SYSTEM_EXCEPTION = 500001;
    public static final String SYSTEM_EXCEPTION_MESSAGE = "system error...";
    public static final int BUSINESS_EXCEPTION = 600001;
    public static final String BUSINESS_EXCEPTION_MESSAGE = "business error...";
    public static final int UNKNOWN_EXCEPTION = 999999;
    public static final String UNKNOWN_EXCEPTION_MESSAGE = "unknown error...";

    // TODO: 2023/4/15 添加各种业务操作对应的code
    public static final int TOKEN_EXPIRED_TIME = 10001;
    public static final String TOKEN_EXPIRED_TIME_MESSAGE = "token expired time...";

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
}
