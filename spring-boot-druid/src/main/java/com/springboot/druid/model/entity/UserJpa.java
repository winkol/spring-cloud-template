package com.springboot.druid.model.entity;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Dong.L
 * @Create: 2019-09-30 11:30
 * @Description: java类描述
 */
@Entity
@Table(name = "user_jpa")
@Data
public class UserJpa {
    @Id
    private Long id;
    @Column(length = 32)
    private String userName;
    @Column(length = 50)
    private String passWord;
    @Column(length = 50)
    private String realName;
    @Column(name = "created_time")
    private String createdTime;
    @Column(name = "updated_time")
    private String updatedTime;

    @Override
    public String toString() {
        return "DEFAULT_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE)
                + "\nSIMPLE_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE)
//                + "\nJSON_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
                + "\nMULTI_LINE_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE)
                + "\nSHORT_PREFIX_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
//                + "\nNO_CLASS_NAME_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                + "\nNO_FIELD_NAMES_STYLE: " + ToStringBuilder.reflectionToString(this, ToStringStyle.NO_FIELD_NAMES_STYLE)
                ;
    }
}
