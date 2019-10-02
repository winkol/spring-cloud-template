package com.springboot.apollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @Author:  Man.Luo
* @Date:    2019/9/11 14:34
* @Description: 启动类
*/
@SpringBootApplication
public class SpringBootApolloApplication {

    /*static {
        System.setProperty("-Dapp.id","luodong001");
        System.setProperty("-Denv","dev");
        System.setProperty("-Ddev.meta","http://192.168.8.151:8080");
    }*/

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApolloApplication.class, args);
    }

}
