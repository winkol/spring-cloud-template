/*
* **************************************************************
* Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
* RIGHTS RESERVED.
* **************************************************************
* PROJECT INFORMATION:
* 项目名称：SpringBootShardingApplication
* 文件名称：SpringBootShardingApplication.java
* 代码说明：启动类
* **************************************************************
* CHANGE HISTORY:
* Author Date Version Reason
* Dong.L 2019/12/24 10:45 v1.0.0 初始创建
*
* **************************************************************
*/
package com.springboot.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 启动类
 * @Project: SpringBootShardingApplication
 * @CreateDate: Created in 2019/12/24 10:45
 * @Author: Dong.L
 */
@MapperScan("com.springboot.sharding.mapper")
@SpringBootApplication
public class SpringBootShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShardingApplication.class, args);
    }

}
