package com.springboot.junit5.mode;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 9:34
 * @Description: java类描述
 */
@Data
public class RequestDataVO<T> {

    @NotBlank(message = "参数code，不能为空")
    private String code;
    private T data;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
