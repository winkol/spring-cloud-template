/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：JinritoutiaoCallBack.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/20 17:48 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.pattern.handler;

import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Project: com.springboot.pattern.handler
 * @CreateDate: Created in 2020/5/20 17:48
 * @Author: Dong.L
 **/
@Component
public class JinritoutiaoCallBack extends CpaCallBack {
    @Override
    Boolean callbackAdvertisers(String str) {
        System.out.println("->> JinritoutiaoCallBack");
        return false;
    }
}
