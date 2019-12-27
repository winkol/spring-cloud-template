/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：IdWorkerTest.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2019/12/27 17:18 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.sharding;

import com.springboot.sharding.util.SnowflakeIdWorker;
import org.junit.Test;

/**
 * @Description: 生成主键ID,唯一键id,分布式ID生成器雪花算法代码实现
 * @Project: com.springboot.sharding
 * @CreateDate: Created in 2019/12/27 17:18
 * @Author: Dong.L
 **/
public class SnowflakeIdWorkerTest {

    @Test
    public void test() {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();
        for (int i = 0; i<100; i++) {
            System.out.println(snowflakeIdWorker.nextId());
        }
    }

}
