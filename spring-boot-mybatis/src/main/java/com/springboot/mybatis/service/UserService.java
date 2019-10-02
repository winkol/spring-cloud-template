package com.springboot.mybatis.service;

import com.springboot.mybatis.mapper.UserMapper;
import com.springboot.mybatis.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Dong.L
 * @Create: 2019-09-30 10:14
 * @Description: java类描述
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User info(int id){
        return userMapper.info(id);
    }

    public List<User> all(){
        return userMapper.all();
    }
}
