/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：HelloControllerMockTest.java
 * 代码说明：mock私有&静态方法
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/9/1 09:44 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.junit5.controller;

import com.springboot.junit5.util.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

/**
 * @Description: mock私有&静态方法
 * @Project: com.springboot.junit5.controller
 * @CreateDate: Created in 2020/9/1 09:44
 * @Author: Dong.L
 **/
@RunWith(PowerMockRunner.class)
@PrepareForTest({StringUtils.class, HelloController.class})
public class HelloControllerMockTest {
    @InjectMocks
    private HelloController controller;

    @Before
    public void setUp() {
        try {
            // 私有属性赋值
            MemberModifier.field(HelloController.class, "test").set(controller, 100);

            // 静态类、方法操作
            PowerMockito.mockStatic(StringUtils.class);
            when(StringUtils.stripHtml(anyString())).thenReturn("aaaaa");
        } catch (Exception ex) {
        }
    }

    @Test
    public void testW() {
        controller.testSup();
    }

    @Test
    public void testPrivate() {
        try {
            HelloController spy = PowerMockito.spy(controller);
            PowerMockito.when(spy, "testPrivate", "123").thenReturn("aa");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
