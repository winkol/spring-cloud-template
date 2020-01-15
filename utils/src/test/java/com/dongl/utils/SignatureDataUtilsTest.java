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

import com.dongl.utils.util.SignatureDataUtils;
import org.junit.Test;

import java.util.Map;

/**
 * @Description: TODO
 * @Project: com.dongl.utils
 * @CreateDate: Created in 2020/1/13 16:10
 * @Author: Dong.L
 **/
public class SignatureDataUtilsTest {

    /**
     * 私key
     */
    private static String priKey = "pri_key";
    /**
     * 公key
     */
    private static String pubKey = "pub_key";
    private static String data = "{'name':'Dong.L', 'phone':'18566762652', 'content':'这是测试'}";

    @Test
    public void test() throws Exception {
        Map<String, String> keyMap = SignatureDataUtils.getKeyPair("Dong.L");
        String sign = SignatureDataUtils.getSignPri(keyMap.get(priKey), data);
        System.out.println("公钥签名验证：" + SignatureDataUtils.verifySign(data, sign, keyMap.get(pubKey)));
        byte[] encryptPub = SignatureDataUtils.encryptByPublicKey(data.getBytes(), keyMap.get(pubKey));
        String encryptStr = SignatureDataUtils.bytesToHexStr(encryptPub);
        System.out.println("公钥加密：" + encryptStr);
        byte[] decryptPub = SignatureDataUtils.encryptByPrivateKey(encryptPub, keyMap.get(priKey));
        String decryptStr = SignatureDataUtils.bytesToHexStr(decryptPub);
        System.out.println("私钥加密：" + decryptStr);
    }

}
