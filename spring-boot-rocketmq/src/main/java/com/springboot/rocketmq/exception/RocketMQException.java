/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：RocketMQException.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/3/2 17:24 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rocketmq.exception;

import com.springboot.rocketmq.constants.ErrorCode;

/**
 * @Description: TODO
 * @Project: com.springboot.rocketmq.exception
 * @CreateDate: Created in 2020/3/2 17:24
 * @Author: Dong.L
 **/
public class RocketMQException extends AppException {
    private static final long serialVersionUID = 1L;

    /**
     * 无参构造函数
     */
    public RocketMQException() {
        super();
    }

    public RocketMQException(Throwable e) {
        super(e);
    }

    public RocketMQException(ErrorCode errorType) {
        super(errorType);
    }

    public RocketMQException(ErrorCode errorCode, String... errMsg) {
        super(errorCode, errMsg);
    }

    /**
     * 封装异常
     *
     * @param errorCode
     * @param errMsg
     * @param isTransfer
     *            是否转换异常信息，如果为false,则直接使用errMsg信息
     */
    public RocketMQException(ErrorCode errorCode, String errMsg, Boolean isTransfer) {
        super(errorCode, errMsg, isTransfer);
    }

    public RocketMQException(ErrorCode errCode, Throwable cause, String... errMsg) {
        super(errCode, cause, errMsg);
    }
}
