package com.springboot.mybatis;

import com.springboot.mybatis.dao.UserJpaDao;
import com.springboot.mybatis.model.entity.User;
import com.springboot.mybatis.model.entity.UserJpa;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DisplayName("测试类")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootMybatisApplicationTests {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserJpaDao userJpaDao;

    @Test
    @DisplayName("获取用户信息")
    public void getInfo() {
        ResponseEntity<String> entity = testRestTemplate.getForEntity("/getInfo/3", String.class);
        System.out.println(entity.getBody());
    }

    @Test
    @DisplayName("获取所有用户信息")
    public void getAll() {
        ResponseEntity<List> entity = testRestTemplate.getForEntity("/getAll", List.class);
        List lists = entity.getBody();
        //  第一种遍历方式：
        lists.forEach(s -> {
            System.out.println(s);
        });
        System.out.println("=====================");
        //第二种遍历方式：
        lists.forEach(s-> System.out.println(s));
        System.out.println("==========================");
        //第三种遍历方式：
        lists.forEach(System.out :: println);
//        System.out.println(entity.getBody());
    }

    @Test
    @DisplayName("jpa测试用户信息")
    public void getJpaInfo(){
        Optional<UserJpa> optional = userJpaDao.findById(1L);
        System.out.println(optional.get().toString());

        System.out.println("===================================");
        userJpaDao.findAll().forEach(System.out :: println);
    }

}
