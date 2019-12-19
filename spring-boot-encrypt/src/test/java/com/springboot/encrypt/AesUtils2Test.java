/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：AesUtils2Test.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2019/12/19 09:22 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.encrypt;

import com.springboot.encrypt.util.AesUtils2;
import org.junit.Test;

/**
 * @Description: TODO
 * @Project: com.springboot.encrypt
 * @CreateDate: Created in 2019/12/19 09:22
 * @Author: Dong.L
 **/
public class AesUtils2Test {
    private static final String KEY = "PAB-123456.POC";

    @Test
    public void test1() {
        String content = "{'repairPhone':'18547854787','customPhone':'12365478965','captchav':'58m7'}";
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + KEY);
        long lStart = System.currentTimeMillis();
        String encrypt = AesUtils2.encrypt(content, KEY);
        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        System.out.println("加密后：" + encrypt);
        lStart = System.currentTimeMillis();
        String decrypt = AesUtils2.decrypt(encrypt, KEY);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
        System.out.println("解密后：" + decrypt);

        System.out.println();
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + KEY);
        lStart = System.currentTimeMillis();
        encrypt = AesUtils2.encrypt(content, KEY, false);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        System.out.println("加密后：" + encrypt);
        lStart = System.currentTimeMillis();
        decrypt = AesUtils2.decrypt(encrypt, KEY, false);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
        System.out.println("解密后：" + decrypt);
    }

}
