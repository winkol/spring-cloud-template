/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：UserService.java
 * 代码说明：用户服务类
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2019/12/24 10:39 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.sharding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.sharding.entity.User;

import java.util.List;

/**
 * @Description: 用户服务类
 * @Project: com.springboot.sharding.service
 * @CreateDate: Created in 2019/12/24 10:39
 * @Author: Dong.L
 **/
public interface UserService extends IService<User> {
    /**
     * 保存用户信息
     * @param entity
     * @return
     */
    @Override
    boolean save(User entity);
    /**
     * 查询全部用户信息
     * @return
     */
    List<User> getUserList();
}
