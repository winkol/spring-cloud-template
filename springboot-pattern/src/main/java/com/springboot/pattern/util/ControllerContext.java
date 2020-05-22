/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：ControllerContext.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/20 16:32 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.pattern.util;

import com.springboot.pattern.handler.AbstractReqTypeHandler;

import java.util.Map;

/**
 * @Description: 处理上下文，根据类型获取相应的处理器
 * @Project: com.springboot.pattern.util
 * @CreateDate: Created in 2020/5/20 16:32
 * @Author: Dong.L
 **/
public class ControllerContext {
    private Map<String, Class> handlerMap;

    public ControllerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    /**
     * @param type
     * @method: getInstance
     * @description: 根据类型获取对应处理类
     * @return: AbstractReqTypeHandler
     * @throws: Exception
     * @author: Dong.L
     * @date: 2020/5/20 16:40
     */
    public AbstractReqTypeHandler getInstance(String type) throws Exception {
        Class clazz = handlerMap.get(type);
        if (clazz == null) {
            throw new Exception("没有定义的类型");
        }
        return (AbstractReqTypeHandler) SpringContextUtils.getBean(clazz);
    }
}
