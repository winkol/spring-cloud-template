/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：EcDsaUtilsTest.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/15 15:41 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils;

import com.dongl.utils.util.EcDsaUtils;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Description: TODO
 * @Project: com.dongl.utils
 * @CreateDate: Created in 2020/1/15 15:41
 * @Author: Dong.L
 **/
public class EcDsaUtilsTest {

    private static String data = "Dong.L test";

    @Test
    public void test() throws Exception {
//        生成公钥私钥1
        KeyPair keyPair1 = EcDsaUtils.getKeyPair();
        PublicKey publicKey1 = keyPair1.getPublic();
        PrivateKey privateKey1 = keyPair1.getPrivate();
        System.out.println(Hex.encodeHexString(publicKey1.getEncoded()));
        System.out.println(Hex.encodeHexString(privateKey1.getEncoded()));
        //        加签验签
        String sign = EcDsaUtils.signECDSA(privateKey1, data);
        System.out.println("sign: " + sign);
        EcDsaUtils.verifyECDSA(publicKey1, sign, data);

        System.out.println("\n\n");

//        生成公钥私钥2
        KeyPair keyPair2 = EcDsaUtils.getKeyPair();
        PublicKey publicKey2 = keyPair2.getPublic();
        PrivateKey privateKey2 = keyPair2.getPrivate();
        System.out.println(Hex.encodeHexString(publicKey2.getEncoded()));
        System.out.println(Hex.encodeHexString(privateKey2.getEncoded()));

        //生成多次的share key一样
        for (int i = 0; i < 10; i++) {
            String sharedKey1 = EcDsaUtils.genSharedKey(publicKey1, privateKey2);
            String sharedKey2 = EcDsaUtils.genSharedKey(publicKey2, privateKey1);
            System.out.println(sharedKey1);
            System.out.println(sharedKey2);
        }
    }

}
