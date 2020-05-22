/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：CpaSourceEnum.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/20 17:46 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.pattern.enums;

/**
 * @Description: TODO
 * @Project: com.springboot.pattern.enums
 * @CreateDate: Created in 2020/5/20 17:46
 * @Author: Dong.L
 **/
public enum CpaSourceEnum {
    /**
     *
     */
    unknown(1,"未知",""),
    guangdiantong(2,"广点通","guangdiantongCallBack"),
    wangxiang(3,"旺翔","wangxiangCallBack"),
    jinritoutiao(4,"今日头条","jinritoutiaoCallBack"),
    tuia(5,"推啊","tuiaCallBack"),
    adjuz(6,"巨掌","adjuzCallBack");

    CpaSourceEnum(int value, String description, String defaultBeanName) {
        this.value = value;
        this.description = description;
        this.defaultBeanName = defaultBeanName;
    }

    private int value;
    private String description;

    /**
     * SpringBean,用于找到对应的对象
     */
    private String defaultBeanName;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getDefaultBeanName() {
        return defaultBeanName;
    }
}
