package com.springboot.apollo.configuration;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: Dong.L
 * @Create: 2019-09-11 10:58
 * @Description: java类描述
 */
public class JavaConfigBean {

    @Value("${userName:张三}")
    private String userName;

    @Value("${timeout:9999}")
    private Integer timeout;

    public String getUserName() {
        return userName;
    }

    public Integer getTimeout() {
        return timeout;
    }
}
