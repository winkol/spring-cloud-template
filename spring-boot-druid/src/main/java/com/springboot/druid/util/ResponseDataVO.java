package com.springboot.druid.util;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 9:36
 * @Description: java类描述
 */
@Data
public class ResponseDataVO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResponseDataVO newResult(Integer code, String message) {
        return new ResponseDataVO(){{
            setCode(code);
            setMessage(message);
        }};
    }

    public static ResponseDataVO newFailResult(String message) {
        return new ResponseDataVO(){{
            setCode(ApiCode.ERROR.getCode());
            setMessage(message);
        }};
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
