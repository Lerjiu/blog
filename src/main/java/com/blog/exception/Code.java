package com.blog.exception;

public class Code {
    public static final int SYSTEM_EXCEPTION = 1001;
    public static final String SYSTEM_EXCEPTION_MESSAGE = "system error...";
    public static final int BUSINESS_EXCEPTION = 2001;
    public static final String BUSINESS_EXCEPTION_MESSAGE = "business error...";
    public static final int UNKNOWN_EXCEPTION = 9999;
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
    public static final int USER_LOGOUT = 40060;
    public static final String USER_LOGOUT_MESSAGE = "user logout success...";

    public static final int FILE_SAVE_EXCEPTION = 50001;
    public static final String FILE_SAVE_EXCEPTION_MESSAGE = "file save error...";
    public static final int FILE_EMPTY_EXCEPTION = 50002;
    public static final String FILE_EMPTY_EXCEPTION_MESSAGE = "file empty error...";

    public static final int FOLLOW = 60000;
    public static final String FOLLOW_MESSAGE = "follow success";
    public static final int FOLLOW_GET_FOLLOWED_LIST = 60010;
    public static final String FOLLOW_GET_FOLLOWED_LIST_MESSAGE = "get followed list success";
    public static final int FOLLOW_GET_FOLLOWER_LIST = 60020;
    public static final String FOLLOW_GET_FOLLOWER_LIST_MESSAGE = "get follower list success;";
    public static final int FOLLOW_DELETE = 60030;
    public static final String FOLLOW_DELETE_MESSAGE = "delete followed success";

    public static final int COMMENT_ADD = 70000;
    public static final String COMMENT_ADD_MESSAGE = "add comment success";
    public static final int COMMENT_GET_LIST = 70010;
    public static final String COMMENT_GET_LIST_MESSAGE = "get comment list success";
    public static final int COMMENT_DELETE = 70020;
    public static final String COMMENT_DELETE_MESSAGE = "delete comment success";

    public static final int FAVORITE_ADD_FOLDER = 80000;
    public static final String FAVORITE_ADD_FOLDER_MESSAGE = "add favorite folder success";
    public static final int FAVORITE_GET_FOLDER_LIST = 80010;
    public static final String FAVORITE_GET_FOLDER_LIST_MESSAGE = "get favorite folder list success";
    public static final int FAVORITE_DELETE_FOLDER = 80020;
    public static final String FAVORITE_DELETE_FOLDER_MESSAGE = "delete favorite folder success";
    public static final int FAVORITE_ADD = 80030;
    public static final String FAVORITE_ADD_MESSAGE = "add favorite success";
    public static final int FAVORITE_EXCEPTION_DUPLICATE_IN_FOLDER = 80031;
    public static final String FAVORITE_EXCEPTION_DUPLICATE_IN_FOLDER_MESSAGE = "exception: favorite duplicate in folder";
    public static final int FAVORITE_GET_LIST = 80040;
    public static final String FAVORITE_GET_LIST_MESSAGE = "get favorite list success";
    public static final int FAVORITE_GET_PAGE_NUM = 80050;
    public static final String FAVORITE_GET_PAGE_NUM_MESSAGE = "get favorite page num success";
    public static final int FAVORITE_GET_PAGE = 80060;
    public static final String FAVORITE_GET_PAGE_MESSAGE = "get page favorite success";
    public static final int FAVORITE_DELETE = 80070;
    public static final String FAVORITE_DELETE_MESSAGE = "delete favorite success";
    public static final int FAVORITE_CHECK_ARTICLE_IN_FOLDERS = 80080;
    public static final String FAVORITE_CHECK_ARTICLE_IN_FOLDERS_MESSAGE = "check article in favorite folders success";

    public static final int ARTICLE_ADD = 90000;
    public static final String ARTICLE_ADD_MESSAGE = "add article success";
    public static final int ARTICLE_DELETE = 90010;
    public static final String ARTICLE_DELETE_MESSAGE = "delete article success";
    public static final int ARTICLE_UPDATE = 90020;
    public static final String ARTICLE_UPDATE_MESSAGE = "update article success";
    public static final int ARTICLE_GET = 90030;
    public static final String ARTICLE_GET_MESSAGE = "get article success";
    public static final int ARTICLE_GET_NUM = 90040;
    public static final String ARTICLE_GET_NUM_MESSAGE = "get article num success";
    public static final int ARTICLE_GET_PAGE = 90050;
    public static final String ARTICLE_GET_PAGE_MESSAGE = "get article page success";
    public static final int ARTICLE_GET_USER_NUM = 90060;
    public static final String ARTICLE_GET_USER_NUM_MESSAGE = "get user article num success";
    public static final int ARTICLE_GET_USER_PAGE = 90070;
    public static final String ARTICLE_GET_USER_PAGE_MESSAGE = "get user article page success";
    public static final int ARTICLE_PAGE_FUZZY_SEARCH = 90080;
    public static final String ARTICLE_PAGE_FUZZY_SEARCH_MESSAGE = "fuzzy search article page success";
    public static final int ARTICLE_UPLOAD_IMG = 90090;
    public static final String ARTICLE_UPLOAD_IMG_MESSAGE = "upload article image success";
    public static final int ARTICLE_THEME_GET_NUM = 90100;
    public static final String ARTICLE_THEME_GET_NUM_MESSAGE = "get theme article num success";
    public static final int ARTICLE_THEME_GET_PAGE = 90110;
    public static final String ARTICLE_THEME_GET_PAGE_MESSAGE = "get page theme article success";
    public static final int ARTICLE_LABEL_GET_NUM = 90120;
    public static final String ARTICLE_LABEL_GET_NUM_MESSAGE = "get label article num success";
    public static final int ARTICLE_LABEL_GET_PAGE = 90130;
    public static final String ARTICLE_LABEL_GET_PAGE_MESSAGE = "get page label article success";
    public static final int ARTICLE_GET_BY_ID_LIST = 90140;
    public static final String ARTICLE_GET_BY_ID_LIST_MESSAGE = "get articles by id list success";
    public static final int ARTICLE_GET_THEME = 90150;
    public static final String ARTICLE_GET_THEME_MESSAGE = "get article theme success";
    public static final int ARTICLE_GET_LABELS = 90160;
    public static final String ARTICLE_GET_LABELS_MESSAGE = "get article labels success";
    public static final int ARTICLE_GET_THEME_BY_IDS = 90170;
    public static final String ARTICLE_GET_THEME_BY_IDS_MESSAGE = "get article theme by ids success";
    public static final int ARTICLE_GET_LABELS_BY_IDS = 90180;
    public static final String ARTICLE_GET_LABELS_BY_IDS_MESSAGE = "get article labels by ids success";

    public static final int DRAFT_ADD = 100000;
    public static final String DRAFT_ADD_MESSAGE = "add draft success";
    public static final int DRAFT_DELETE = 100010;
    public static final String DRAFT_DELETE_MESSAGE = "delete draft success";
    public static final int DRAFT_UPDATE = 100020;
    public static final String DRAFT_UPDATE_MESSAGE = "update draft success";
    public static final int DRAFT_GET = 100030;
    public static final String DRAFT_GET_MESSAGE = "get draft success";
    public static final int DRAFT_GET_USER_NUM = 100040;
    public static final String DRAFT_GET_USER_NUM_MESSAGE = "get user draft num success";
    public static final int DRAFT_GET_USER_PAGE = 100050;
    public static final String DRAFT_GET_USER_PAGE_MESSAGE = "get user drafts success";
    public static final int DRAFT_PUBLISH = 100060;
    public static final String DRAFT_PUBLISH_MESSAGE = "publish draft success";
    public static final int DRAFT_GET_THEME = 100070;
    public static final String DRAFT_GET_THEME_MESSAGE = "get draft theme success";
    public static final int DRAFT_GET_THEME_BY_IDS = 100080;
    public static final String DRAFT_GET_THEME_BY_IDS_MESSAGE = "get draft theme by ids success";
    public static final int DRAFT_GET_LABELS = 100090;
    public static final String DRAFT_GET_LABELS_MESSAGE = "get draft labels success";
    public static final int DRAFT_GET_LABELS_BY_IDS = 100100;
    public static final String DRAFT_GET_LABELS_BY_IDS_MESSAGE = "get draft labels by ids success";

    public static final int LABEL_GET_HOT = 110110;
    public static final String LABEL_GET_HOT_MESSAGE = "get hot labels success";
}