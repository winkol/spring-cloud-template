package com.springboot.mybatis.controller;

import com.springboot.mybatis.model.entity.User;
import com.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Dong.L
 * @Create: 2019-09-30 10:13
 * @Description: java类描述
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getInfo/{id}", method = RequestMethod.GET)
    public String getInfo(@PathVariable int id){
        System.out.println("->> getInfo: " + id);
        User user = userService.info(id);
        return user.toString();
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<User> getInfo(){
        System.out.println("->> getAll");
        return userService.all();
    }
}
