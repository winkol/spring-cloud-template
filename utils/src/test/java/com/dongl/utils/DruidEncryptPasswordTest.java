/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：DruidEncryptPasswordTest.java
 * 代码说明：durid加密
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/13 11:02 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Description: durid加密
 * @Project: com.dongl.utils
 * @CreateDate: Created in 2020/1/13 11:02
 * @Author: Dong.L
 **/
@Slf4j
public class DruidEncryptPasswordTest {

    @Test
    @ApiOperation("druid密码加密生成")
    public void testDruidPwd() throws Exception {
        String password = ConfigTools.encrypt("root");
        log.info("password = {}", password);
    }

    @Test
    @ApiOperation("druid公私钥串")
    public void testEncryptGroup() throws Exception {
        String password = "root";
        String[] arr = ConfigTools.genKeyPair(512);
        log.info("privateKey: {}", arr[0]);
        log.info("publicKey: {}", arr[1]);
        log.info("password: {}", ConfigTools.encrypt(arr[0], password));
    }

}
