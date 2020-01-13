package com.dongl.utils.exception;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 14:56
 * @Description: 工具/转换异常
 */
public class InnerException extends RuntimeException {
    public InnerException() {
        super();
    }

    public InnerException(String message) {
        super(message);
    }

    public InnerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InnerException(Throwable cause) {
        super(cause);
    }

    public InnerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
