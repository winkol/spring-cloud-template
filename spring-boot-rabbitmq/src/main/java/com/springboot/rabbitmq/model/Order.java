/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：springboot-docker
 * 文件名称：Order.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/9 18:10 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rabbitmq.model;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Project: com.ld.rabbitmq.model
 * @CreateDate: Created in 2020/4/9 18:10
 * @Author: Dong.L
 **/
public class Order implements Serializable {
    private String id;
    private String name;

    public Order() {
    }
    public Order(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
