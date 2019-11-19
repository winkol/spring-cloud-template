package com.springboot.druid.util;

import com.alibaba.druid.filter.config.ConfigTools;

/**
* @Author:  Man.Luo
* @Date:    2019/11/17 15:39
* @Description: 数据库密码加密
*/
public class TestDruidEncryptPassword {

    public static void main(String[] args) throws Exception {
        String password = ConfigTools.encrypt("root");
        System.out.println("password = " + password);
    }

}