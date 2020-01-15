/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：SnowflakeKeyGeneratorUtilsTest.java
 * 代码说明：雪花唯一号生成测试
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/15 09:24 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils;

import com.dongl.utils.util.SnowflakeKeyGeneratorUtils;
import org.junit.Test;

/**
 * @Description: 雪花唯一号生成测试
 * @Project: com.dongl.utils
 * @CreateDate: Created in 2020/1/15 09:24
 * @Author: Dong.L
 **/
public class SnowflakeKeyGeneratorUtilsTest {
    /**
     * workId默认从机器的IP地址生成，故如果对ID的唯一性有很高要求，
     * 如果同一台机器上运行多个服务，建议用户自己配置workId。
     */
    private static final long WORKER_ID = 127L;

    @Test
    public void test() {
        SnowflakeKeyGeneratorUtils snowflakeKeyGeneratorUtils = new SnowflakeKeyGeneratorUtils();
        SnowflakeKeyGeneratorUtils.setWorkerId(WORKER_ID);
        for (int i = 0; i < 10; i++) {
            System.out.println("key: " + snowflakeKeyGeneratorUtils.generateKey());
        }
    }
}
