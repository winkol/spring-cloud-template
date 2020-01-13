/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：SignatureDataUtilsTest.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/13 16:10 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils;

import com.dongl.utils.service.GenerateKeyPairService;
import org.junit.Test;

/**
 * @Description: TODO
 * @Project: com.dongl.utils
 * @CreateDate: Created in 2020/1/13 16:10
 * @Author: Dong.L
 **/
public class SignatureDataUtilsTest {

    @Test
    public void test() {
        GenerateKeyPairService.run("abckey");
    }

}
