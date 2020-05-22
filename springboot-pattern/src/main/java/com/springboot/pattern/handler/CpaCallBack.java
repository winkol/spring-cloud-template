/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：CpaCallBack.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/20 17:47 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.pattern.handler;

/**
 * @Description: TODO
 * @Project: com.springboot.pattern.handler
 * @CreateDate: Created in 2020/5/20 17:47
 * @Author: Dong.L
 **/
public abstract class CpaCallBack {
    /**
     * 回调广告商
     */
    abstract Boolean callbackAdvertisers(String str);
}
