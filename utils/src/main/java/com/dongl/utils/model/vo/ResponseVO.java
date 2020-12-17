package com.dongl.utils.model.vo;

import com.dongl.utils.common.ApiCode;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Author: Dong.L
 * @Date: 2019/11/22 15:44
 * @Description: 响应VO
 */
@Data
public class ResponseVO<T> {
    /**
     * 响应code
     */
    private Integer code;
    /**
     * 响应描述
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    private ResponseVO(){}

    private ResponseVO(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    private ResponseVO(ApiCode apiCode){
        this.code = apiCode.getCode();
        this.message = apiCode.getMessage();
    }

    private ResponseVO(ApiCode apiCode, T t){
        this.code = apiCode.getCode();
        this.message = apiCode.getMessage();
        this.data = t;
    }

    private ResponseVO(Integer code, String message, T t){
        this.code = code;
        this.message = message;
        this.data = t;
    }

    /**
     * @param code    状态
     * @param message 描述
     * @return 返回数据VO
     * @Author Dong.L
     * @Date 2019/11/22 15:45
     * @Description: 成功通用处理
     */
    public static ResponseVO newResult(Integer code, String message) {
        return new ResponseVO(code, message);
    }

    /**
     * @param apiCode 状态码类型
     * @return 返回数据VO
     * @Author Dong.L
     * @Date 2019/11/22 15:45
     * @Description: 成功通用处理
     */
    public static ResponseVO newResult(ApiCode apiCode) {
        return new ResponseVO(apiCode.getCode(), apiCode.getMessage());
    }

    /**
     * @param apiCode 状态码类型
     * @param data    返回数据
     * @return 返回数据VO
     * @Author Dong.L
     * @Date 2019/11/22 15:45
     * @Description: 成功通用处理
     */
    public static ResponseVO newResult(ApiCode apiCode, Object data) {
        return new ResponseVO(apiCode, data);
    }

    /**
     * @param code    状态
     * @param message 描述
     * @param data    返回数据
     * @return 返回数据VO
     * @Author Dong.L
     * @Date 2019/11/22 15:45
     * @Description: 成功通用处理
     */
    public static ResponseVO newResult(Integer code, String message, Object data) {
        return new ResponseVO(code, message, data);
    }

    /**
     * @param message 描述
     * @return 返回数据VO
     * @Author Dong.L
     * @Date 2019/11/22 15:45
     * @Description: 失败通用处理
     */
    public static ResponseVO newFailResult(String message) {
        return new ResponseVO(ApiCode.ERROR.getCode(), message);
    }

    public static ResponseVO newFailResult(ApiCode apiCode) {
        return new ResponseVO(apiCode);
    }

    public static ResponseVO newFailResult(ApiCode apiCode, String message) {
        return new ResponseVO(apiCode.getCode(), message);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
