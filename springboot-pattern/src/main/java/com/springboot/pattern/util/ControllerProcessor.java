/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：ControllerProcessor.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/20 16:41 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.pattern.util;

import com.google.common.collect.Maps;
import com.springboot.pattern.annotation.ReqType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: TODO
 * @Project: com.springboot.pattern.util
 * @CreateDate: Created in 2020/5/20 16:41
 * @Author: Dong.L
 **/
@Slf4j
@Component
public class ControllerProcessor implements BeanFactoryPostProcessor {

    private static final String CONTROLLER_PACKAGE = "com.springboot.pattern.handler";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, Class> handlerMap = Maps.newHashMapWithExpectedSize(4);
        ClassScaner.scan(CONTROLLER_PACKAGE, ReqType.class).forEach(clazz -> {
            ReqType reqType = clazz.getAnnotation(ReqType.class);
            String type = reqType.value().getCode();
            handlerMap.put(type, clazz);
            log.info("->> reqType: {}", handlerMap);
        });
        ControllerContext context = new ControllerContext(handlerMap);
        beanFactory.registerSingleton(ControllerContext.class.getName(), context);
    }
}
