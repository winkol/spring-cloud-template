package com.springboot.druid.exception;

import com.springboot.druid.util.ApiCode;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 14:58
 * @Description: 参数异常类
 */
public class ParamIncorrectException extends RuntimeException {
    private ApiCode apiCode;

    public ParamIncorrectException() {
        super();
    }

    public ParamIncorrectException(String message) {
        super(message);
        apiCode = ApiCode.ERROR;
    }

    public ParamIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamIncorrectException(Throwable cause) {
        super(cause);
    }

    public ParamIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ParamIncorrectException(ApiCode apiCode){
        super(apiCode.getMessage());
        this.apiCode = apiCode;
    }

    public ParamIncorrectException(ApiCode apiCode, String message){
        super(message);
        this.apiCode = apiCode;
    }

    public ApiCode getApiCode() {
        return apiCode;
    }

    public void setApiCode(ApiCode apiCode) {
        this.apiCode = apiCode;
    }
}
