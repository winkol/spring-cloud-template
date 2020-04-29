/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：MockControllerTest.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/21 14:39 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.junit5.controller;

import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Description: TODO
 * @Project: com.springboot.junit5.controller
 * @CreateDate: Created in 2020/4/21 14:39
 * @Author: Dong.L
 **/
@RunWith(SpringJUnit4ClassRunner.class)
public class MockControllerTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private MockController mockController;

    @Before
    public void setUp() {
        Mockito.when(restTemplate.postForObject(Mockito.anyString(), Mockito.anyMap(), Mockito.any()))
                .thenReturn(ImmutableMap.of("status", "200"));
    }

    @Test
    public void testHallo(){
        Map<String, Object> map = mockController.hallo("mock abc.");
        Assert.assertTrue(map.get("status").equals("200"));
    }
}
