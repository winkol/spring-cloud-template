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
 * Dong.L 2019/12/18 18:16 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.encrypt;

import com.springboot.encrypt.util.AesUtils;
import org.junit.Test;

/**
 * @Description: TODO
 * @Project: com.springboot.encrypt
 * @CreateDate: Created in 2019/12/18 18:16
 * @Author: Dong.L
 **/
public class AesUtilsTest {

    @Test
    public void test() {
        String content = "{'repairPhone':'18547854787','customPhone':'12365478965','captchav':'58m7'}";
        String encryptStr = AesUtils.encrypt(content);
        System.out.println("加密后的结果："+ encryptStr);

        String decryptStr = AesUtils.decrypt(encryptStr);
        System.out.println("解密后的结果："+ decryptStr);
    }
}
