package com.springboot.rabbitmq.exception;

import com.springboot.rabbitmq.model.ApiCode;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 15:41
 * @Description: 逻辑处理异常类
 */
public class ServiceException extends RuntimeException {

    private ApiCode apiCode;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
        apiCode = ApiCode.ERROR;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(ApiCode apiCode) {
        super(apiCode.getMessage());
        this.apiCode = apiCode;
    }

    public ServiceException(ApiCode apiCode, String errMsg) {
        super(errMsg);
        this.apiCode = apiCode;
    }

    public ApiCode getApiCode() {
        return apiCode;
    }

    public void setApiCode(ApiCode apiCode) {
        this.apiCode = apiCode;
    }
}
