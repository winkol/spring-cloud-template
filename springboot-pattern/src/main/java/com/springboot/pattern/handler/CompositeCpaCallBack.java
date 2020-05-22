/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：CompositeCpaCallBack.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/20 17:49 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.pattern.handler;

import com.springboot.pattern.enums.CpaSourceEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Project: com.springboot.pattern.handler
 * @CreateDate: Created in 2020/5/20 17:49
 * @Author: Dong.L
 **/
@Component
public class CompositeCpaCallBack implements ApplicationContextAware {
    private  ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public Boolean callbackAdvertisers(String str) {
        try {
            CpaCallBack callBack = getCpaCallBack(str);
            return callBack.callbackAdvertisers(str);
        } catch (Exception e) {
            System.err.println("回调异常," + e);
            return false;
        }
    }

    public CpaCallBack getCpaCallBack(String source){
        CpaSourceEnum cpaSourceEnum = CpaSourceEnum.valueOf(source);
        return  (CpaCallBack) applicationContext.getBean(cpaSourceEnum.getDefaultBeanName());
    }
}
