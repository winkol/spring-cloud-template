package com.springboot.mybatis.mapper;

import com.springboot.mybatis.model.entity.User;

import java.util.List;

/**
 * @Author: Dong.L
 * @Create: 2019-09-30 10:14
 * @Description: java类描述
 */
public interface UserMapper {

    User info(int id);

    List<User> all();
}
