package com.springboot.junit5.exception;

import com.springboot.junit5.util.ResultCode;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 14:58
 * @Description: java类描述
 */
public class ParamIncorrectException extends RuntimeException {
    private ResultCode resultCode;

    public ParamIncorrectException() {
        super();
    }

    public ParamIncorrectException(String message) {
        super(message);
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

    public ParamIncorrectException(ResultCode resultCode){
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public ParamIncorrectException(ResultCode resultCode, String message){
        super(message);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
