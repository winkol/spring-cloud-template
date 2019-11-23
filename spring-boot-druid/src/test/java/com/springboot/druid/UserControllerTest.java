package com.springboot.druid;

import com.springboot.druid.dao.UserJpaDao;
import com.springboot.druid.model.base.Paging;
import com.springboot.druid.model.base.Query;
import com.springboot.druid.model.entity.UserJpa;
import com.springboot.druid.model.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @Author: Dong.L
 * @Create: 2019-11-20 10:24
 * @ClassName: ${name}.class
 * @Description: java类描述
 */
@Slf4j
@RunWith(SpringRunner.class)
@DisplayName("测试类")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private UserJpaDao userJpaDao;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("jpa测试用户信息")
    public void getJpaInfo() {
        Optional<UserJpa> optional = userJpaDao.findById(1L);
        log.info(optional.get().toString());

        log.info("===================================");
        userJpaDao.findAll().forEach(System.out::println);
    }

    @Test
    @DisplayName("Mapper获取用户信息")
    public void getMapperInfo() {
        ResponseEntity<String> entity = testRestTemplate.getForEntity("/testMap/3", String.class);
        log.info(entity.getBody());
    }

    @Test
    @DisplayName("翻页测试")
    public void getPageAll() {
        Query query = new Query();
        Paging page = new Paging(1, 3);
        query.setPage(page);
        query.setOrderColumn("created_time");
        query.setOrderType("desc");
        ResponseVO responseVO = testRestTemplate.postForObject("/testPage", query, ResponseVO.class);
        log.info(responseVO.toString());
    }
}
