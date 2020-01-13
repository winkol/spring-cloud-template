/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：AesUtilsTest.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/13 11:14 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils;

import com.dongl.utils.util.AesUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Description: TODO
 * @Project: com.dongl.utils
 * @CreateDate: Created in 2020/1/13 11:14
 * @Author: Dong.L
 **/
@Slf4j
public class AesUtilsTest {
    private static final String KEY = String.format("%s%s", "DONG.L-", System.currentTimeMillis());

    @Test
    @ApiOperation("对称加解密测试")
    public void test() {
        String content = "{'name':'Dong.L', 'phone':'18566762652','test':'这是个测试'}";
        log.info("->> 加密前：{}", content);
        long lStart = System.currentTimeMillis();
        String encrypt = AesUtils.encrypt(content, KEY, false);
        long lUseTime = System.currentTimeMillis() - lStart;
        log.info("->> 加密耗时：{}毫秒", lUseTime);
        log.info("->> 加密后：{}", encrypt);

        log.info("----------------------------------------------------------------------");
        lStart = System.currentTimeMillis();
        String decrypt = AesUtils.decrypt(encrypt, KEY, false);
        lUseTime = System.currentTimeMillis() - lStart;
        log.info("->> 解密耗时：{}毫秒", lUseTime);
        log.info("->> 解密后：{}", decrypt);
    }
}
