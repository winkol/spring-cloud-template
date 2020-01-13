package com.dongl.utils.common;

/**
 * @Author:  Dong.L
 * @Date:    2019/11/19 15:10
 * @Description: 返回的状态码
 */
public enum ApiCode {
    /**
     *
     */
    OK(0, "成功"),
    ERROR(-1, "系统繁忙"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "访问没有权限"),
    NOT_FOUND(404, "请求的URL地址不存在"),

    CONNECT_ERROR(50001, "服务连接异常"),
    CONNECT_TIMEOUT(50002, "连接超时"),

    MISSING_PARAMETER(40001, "缺少参数"),
    PARAMETER_TYPE_ERROR(40003, "参数类型错误"),
    INVALID_PARAMETERS(40002, "无效的参数"),
    FORMAT_ERROR(40003, "参数解析异常"),
    JSON_FORMAT_ERROR(40003, "json解析异常"),
    XML_FORMAT_ERROR(40004, "xml解析异常"),
    ILLEGAL_REQUEST(40005, "非法的请求"),

    MISSING_ACCESS_TOKEN(41000, "缺少accessToken参数"),
    INVALID_ACCESS_TOKEN(41001, "无效的accessToken"),
    INVALID_REFRESH_TOKEN(41002, "无效的refreshToken参数"),
    ACCESS_TOKEN_EXPIRED(41003, "accessToken已过期"),

    CONFIGURED_ERROR(42000, "配置错误"),
    MISSING_URL_PARAMETER(42001, "缺少url参数"),
    URL_PARAMETERS_WRONGFUL(42002, "url参数不合法"),
    URL_CONFIGURED_NOT_EXIST(42003, "url配置不存在"),
    STATUS_CODE_NOT_EXIST(42004, "缺少响应体状态码参数"),
    MISSING_RESP_STATUS_CODE(42005, "响应体状态码不存在"),
    EXPRESSION_ANALYTIC_ERROR(42006, "表达式解析失败"),
    STATUS_CODE_ILLEGAL(42007, "被代理方响应非法请求"),
    STATUS_CODE_ERROR(42008, "被代理方响应错误"),
    MISSING_UNIQUE_IDENTIFICATION_PARAMETER(42009, "缺少唯一标识参数"),
    UNIQUE_IDENTIFICATION_ALREADY_EXISTS(42010, "唯一标识已存在"),
    INVALID_UNIQUE_IDENTIFICATION(42011, "无效的唯一标识"),

    PARAM_ERROR(60002, "参数错误"),
    ;

    private final Integer code;
    private final String message;

    ApiCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}