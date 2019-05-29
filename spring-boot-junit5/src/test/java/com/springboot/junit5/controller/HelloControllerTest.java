package com.springboot.junit5.controller;

import com.springboot.junit5.base.BaseTest;
import com.springboot.junit5.mode.RequestDataVO;
import com.springboot.junit5.mode.ResponseDataVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Author: Dong.L
 * @Create: 2019-05-27 11:38
 * @Description: java类描述
 */
@Slf4j
@DisplayName("测试接口")
@ExtendWith(SpringExtension.class)
class HelloControllerTest extends BaseTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        baseUrl = "/test";
        log.info("->> HelloControllerTest.start");
    }

    @AfterEach
    void tearDown() {
        log.info("->> HelloControllerTest.end");
    }

//    @Test
    @DisplayName("测试方法1")
    @ParameterizedTest
    @ValueSource(strings = {"testJson"})
    void hi(String fileName) {
        baseUrl += "/hi";
        RequestDataVO requestDataVO = requestDataHandle(fileName);
        log.info("->> {}", requestDataVO.toString());
        //ResponseEntity<ResponseDataVO> responseEntity = restTemplate.getForEntity(baseUrl, ResponseDataVO.class, requestDataVO);
        ResponseEntity<ResponseDataVO> responseEntity = restTemplate.postForEntity(baseUrl, requestDataVO, ResponseDataVO.class);
        //responseHandle(responseEntity);
    }
    @DisplayName("测试方法2")
    @ParameterizedTest
    @ValueSource(strings = {"testJson"})
    void hi2(String fileName) {
        baseUrl += "/hi3";
        RequestDataVO requestDataVO = requestDataHandle(fileName);
        log.info("->> {}", requestDataVO.toString());
        ResponseEntity<ResponseDataVO> responseEntity = restTemplate.postForEntity(baseUrl, requestDataVO, ResponseDataVO.class);
        responseHandle(responseEntity);
    }
}