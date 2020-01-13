/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：SnowflakeIdWorkerTest.java
 * 代码说明：雪花算法测试
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/13 14:19 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils;

import com.dongl.utils.util.SnowflakeIdWorker;
import org.junit.Test;

/**
 * @Description: 雪花算法测试
 * @Project: com.dongl.utils
 * @CreateDate: Created in 2020/1/13 14:19
 * @Author: Dong.L
 **/
public class SnowflakeIdWorkerTest {
    @Test
    public void test() {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();
        for (int i = 0; i < 100; i++) {
            System.out.println(snowflakeIdWorker.nextId());
        }
    }
}
