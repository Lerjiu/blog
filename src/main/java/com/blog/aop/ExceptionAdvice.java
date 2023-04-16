package com.blog.aop;

import com.blog.controller.response.Response;
import com.blog.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Response handleSystemException(SystemException systemException) {
        systemException.printStackTrace();
        return Response.fail(systemException.getExceptionCode(), systemException.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Response handleBusinessException(BusinessException businessException) {
        businessException.printStackTrace();
        return Response.fail(businessException.getExceptionCode(), businessException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception exception) {
        exception.printStackTrace();
        return Response.fail(Code.UNKNOWN_EXCEPTION, Code.UNKNOWN_EXCEPTION_MESSAGE);
    }
}