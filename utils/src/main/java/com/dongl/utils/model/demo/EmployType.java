/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：EmployType.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2021/4/13 17:55 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.model.demo;

/**
 * @Description: TODO
 * @Project: com.dongl.utils.model.demo
 * @CreateDate: Created in 2021/4/13 17:55
 * @Author: Dong.L
 **/
public enum EmployType {
    /**
     *
     */
    BANK(1, "行编"), FINSERVER(2, "金服"),
    UNKNOWN(2, "金服")
    ;

    private int code;
    private String name;

    EmployType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EmployType fromName(String name) {
        for (EmployType type : values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
