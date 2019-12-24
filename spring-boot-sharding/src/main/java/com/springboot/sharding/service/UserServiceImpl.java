/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：UserServiceImpl.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2019/12/24 10:40 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.sharding.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.sharding.entity.User;
import com.springboot.sharding.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @Project: com.springboot.sharding.service
 * @CreateDate: Created in 2019/12/24 10:40
 * @Author: Dong.L
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public List<User> getUserList() {
        return baseMapper.selectList(Wrappers.<User>lambdaQuery().orderByDesc(User::getId));
    }
}
