/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：ErrorCode.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/3/2 17:27 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rocketmq.constants;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Project: com.springboot.rocketmq.constants
 * @CreateDate: Created in 2020/3/2 17:27
 * @Author: Dong.L
 **/
public interface ErrorCode extends Serializable {
    /**
     * 错误码
     *
     * @return
     */
    String getCode();

    /**
     * 错误信息
     *
     * @return
     */
    String getMsg();
}
