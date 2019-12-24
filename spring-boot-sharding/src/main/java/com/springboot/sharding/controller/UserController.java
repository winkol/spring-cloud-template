/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：UserController.java
 * 代码说明：用户测试控制类
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2019/12/24 10:43 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.sharding.controller;

import com.springboot.sharding.entity.User;
import com.springboot.sharding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 用户测试控制类
 * @Project: com.springboot.sharding.controller
 * @CreateDate: Created in 2019/12/24 10:43
 * @Author: Dong.L
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/select")
    public List<User> select() {
        return userService.getUserList();
    }

//    @GetMapping("/insert")
    @PostMapping("/insert")
    public Boolean insert(@RequestBody User user) {
        return userService.save(user);
    }
}
