/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：ReqTypeEnum.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/20 16:24 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.pattern.enums;

/**
 * @Description: TODO
 * @Project: com.springboot.pattern.enums
 * @CreateDate: Created in 2020/5/20 16:24
 * @Author: Dong.L
 **/
public enum ReqTypeEnum {
    /**
     * UPLOAD-上传，DOWNLOAD-下载
     */
    UPLOAD("UPLOAD"),
    DOWNLOAD("DOWNLOAD"),
    ;
    private String code;

    ReqTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    /**
     * @param code
     * @method: getReqTypeEnumByCode
     * @description: 获取请求枚举类型
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/5/20 16:27
     */
    public static ReqTypeEnum getReqTypeEnumByCode(String code) {
        for (ReqTypeEnum reqTypeEnum : values()) {
            if (code.compareTo(reqTypeEnum.getCode()) == 0) {
                return reqTypeEnum;
            }
        }
        return null;
    }
}
