/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：RocketMQErrorEnum.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/3/2 17:28 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rocketmq.constants;

/**
 * @Description: TODO
 * @Project: com.springboot.rocketmq.constants
 * @CreateDate: Created in 2020/3/2 17:28
 * @Author: Dong.L
 **/
public enum RocketMQErrorEnum implements ErrorCode {
    /******** 公共 ********/
    PARAMM_NULL("MQ_001", "参数为空"),

    /******** 生产者 *******/

    /******** 消费者 *******/
    NOT_FOUND_CONSUMESERVICE("MQ_100", "根据topic和tag没有找到对应的消费服务"), HANDLE_RESULT_NULL("MQ_101", "消费方法返回值为空"), CONSUME_FAIL("MQ_102", "消费失败");

    private String code;
    private String msg;

    private RocketMQErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
