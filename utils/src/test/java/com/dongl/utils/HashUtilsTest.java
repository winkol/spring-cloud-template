/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：HashUtilsTest.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/13 15:14 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils;

import com.dongl.utils.util.HashUtils;
import org.junit.Test;

/**
 * @Description: hash算法测试
 * @Project: com.dongl.utils
 * @CreateDate: Created in 2020/1/13 15:14
 * @Author: Dong.L
 **/
public class HashUtilsTest {

    @Test
    public void test() {
        System.out.println("原内容：hash算法测试");
        System.out.println("hash后内容：" + HashUtils.hashUnsigned("hash算法测试"));
    }

}
