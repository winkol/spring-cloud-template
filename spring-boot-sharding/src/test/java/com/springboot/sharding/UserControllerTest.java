/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：UserControllerTest.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2019/12/24 10:59 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.sharding;

import com.springboot.sharding.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description: TODO
 * @Project: com.springboot.sharding
 * @CreateDate: Created in 2019/12/24 10:59
 * @Author: Dong.L
 **/
@Slf4j
@RunWith(SpringRunner.class)
@DisplayName("测试类")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testTemplate;

    @Test
    public void insert() {
        List<User> users = testTemplate.getForObject("/select", List.class);
        System.out.println(users);

        int size = users.size() + 1;
        System.out.println(size);

        User user = new User();
        user.setId(size);
        user.setAge(size + 5);
        user.setName("jack" + size);
        boolean bool = testTemplate.postForObject("/insert", user, boolean.class);
        System.out.println(bool);
    }

    @Test
    public void select() {
        List<User> users = testTemplate.getForObject("/select", List.class);
        System.out.println(users);
    }

}
