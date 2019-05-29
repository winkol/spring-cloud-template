package com.springboot.junit5.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 16:39
 * @Description: mock使用（https://www.cnblogs.com/wangtj-19/p/5822369.html）
 */
@Slf4j
@DisplayName("测试接口")
@ExtendWith(MockitoExtension.class)
public class MockTest {
    @Mock
    private List list;

    @Test
    public void shouldDoSomething() {
        list.add(100);
        log.info("->> list: {}", list);
    }
}
