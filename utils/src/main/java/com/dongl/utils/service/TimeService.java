/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：TimeService.java
 * 代码说明：时间服务类
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/14 17:54 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.service;

/**
 * @Description: 时间服务类
 * @Project: com.dongl.utils.service
 * @CreateDate: Created in 2020/1/14 17:54
 * @Author: Dong.L
 **/
public class TimeService {
    private volatile boolean isClock = false;

    public TimeService() {
    }

    /**
     * @param clock
     * @method: setClock
     * @description: 设置时钟
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/14 17:57
     */
    public void setClock(boolean clock) {
        this.isClock = clock;
    }

    /**
     * @param
     * @method: getCurrentMillis
     * @description: 获取当前时间戳
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/14 17:56
     */
    public long getCurrentMillis() {
        return this.isClock ? SystemClock.now() : System.currentTimeMillis();
    }
}
