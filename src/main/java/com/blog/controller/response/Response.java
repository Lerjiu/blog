package com.blog.controller.response;

public class Response {
    public static final int RESPONSE_SUCCESS = 0;
    public static final int RESPONSE_FAIL = 1;
    private int status;
    private int code;
    private String message;

    public Response(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Response success(int code) {
        return new Response(RESPONSE_SUCCESS, code, "success");
    }

    public static Response success(int code, String message) {
        return new Response(RESPONSE_SUCCESS, code, message);
    }

    public static Response fail(int code) {
        return new Response(RESPONSE_FAIL, code, "fail");
    }

    public static Response fail(int code, String message) {
        return new Response(RESPONSE_FAIL, code, message);
    }
}
