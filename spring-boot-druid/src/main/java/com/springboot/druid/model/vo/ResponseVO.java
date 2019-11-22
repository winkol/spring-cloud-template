package com.springboot.druid.model.vo;

import com.springboot.druid.util.ApiCode;
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

    /**
     * @param code    状态
     * @param message 描述
     * @return 返回数据VO
     * @Author Dong.L
     * @Date 2019/11/22 15:45
     * @Description: 成功通用处理
     */
    public static ResponseVO newResult(Integer code, String message) {
        return new ResponseVO() {{
            setCode(code);
            setMessage(message);
        }};
    }

    /**
     * @param apiCode 状态码类型
     * @return 返回数据VO
     * @Author Dong.L
     * @Date 2019/11/22 15:45
     * @Description: 成功通用处理
     */
    public static ResponseVO newResult(ApiCode apiCode) {
        return new ResponseVO() {{
            setCode(apiCode.getCode());
            setMessage(apiCode.getMessage());
        }};
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
        return new ResponseVO() {{
            setCode(apiCode.getCode());
            setMessage(apiCode.getMessage());
            setData(data);
        }};
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
        return new ResponseVO() {{
            setCode(code);
            setMessage(message);
            setData(data);
        }};
    }

    /**
     * @param message 描述
     * @return 返回数据VO
     * @Author Dong.L
     * @Date 2019/11/22 15:45
     * @Description: 失败通用处理
     */
    public static ResponseVO newFailResult(String message) {
        return new ResponseVO() {{
            setCode(ApiCode.ERROR.getCode());
            setMessage(message);
        }};
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
