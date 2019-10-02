package com.springboot.mybatis.model.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Author: Dong.L
 * @Create: 2019-09-30 10:06
 * @Description: java类描述
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;

    @Override
    public String toString() {
        return "DEFAULT_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE)
                + "\nSIMPLE_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE)
                + "\nJSON_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
                + "\nMULTI_LINE_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE)
                + "\nSHORT_PREFIX_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
                + "\nNO_CLASS_NAME_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                + "\nNO_FIELD_NAMES_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.NO_FIELD_NAMES_STYLE)
                ;
    }
}
