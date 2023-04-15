package com.blog.controller.response;

public class DataResponse extends Response {
    private Object data;

    public DataResponse(int status, int code, String message, Object data) {
        super(status, code, message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static DataResponse success(int code, Object data) {
        return new DataResponse(RESPONSE_SUCCESS, code, "success", data);
    }

    public static DataResponse success(int code, String message, Object data) {
        return new DataResponse(RESPONSE_SUCCESS, code, message, data);
    }
}
