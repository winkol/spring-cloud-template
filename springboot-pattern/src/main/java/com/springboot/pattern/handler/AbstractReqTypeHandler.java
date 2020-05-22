/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：AbstractReqTypeHandler.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/20 16:35 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.pattern.handler;

import java.util.Map;

/**
 * @Description: 类型处理抽象类
 * @Project: com.springboot.pattern.handler
 * @CreateDate: Created in 2020/5/20 16:35
 * @Author: Dong.L
 **/
public abstract class AbstractReqTypeHandler {

    /**
     * @method:
     * @description: 业务处理
     * @param reqType
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/5/20 17:18
     */
    public abstract Map<String, Object> handler(String reqType);

}
