package com.springboot.junit5.util;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 15:00
 * @Description: java类描述
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS("R200", "SUCCESS"),
    /**
     * 参数错误
     */
    PARAM_ERROR("R301", "参数错误"),
    /**
     * 参数错误
     */
    FAIL("R500", "FAIL"),
    ;

    private String code;
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
