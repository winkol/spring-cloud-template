/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：AesUtils.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2019/12/18 18:12 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.encrypt.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @Description: TODO
 * @Project: com.springboot.encrypt.util
 * @CreateDate: Created in 2019/12/18 18:12
 * @Author: Dong.L
 **/
public class AesUtils {
    // 生成key需要的密码
    private static final String MiddleKey = "PAB-123456_POC";

    public static String encrypt(String password) {
        try {
            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getKey());
            byte[] result = cipher.doFinal(password.getBytes());
            // return Hex.encodeHexString(result);
            // 通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
     * encryptedPassword:待解密的密文
     */
    public static String decrypt(String encryptedPassword) {
        try {
            // 解密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getKey());
            // byte[] result = cipher.doFinal(encryptedPassword.getBytes());
            byte[] result = cipher.doFinal(Base64.decodeBase64(encryptedPassword));
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static Key key;

    public static Key getKey() {
        if (key == null) {
            try {
                // 生成KEY ,AES 要求密钥长度为 128
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                secureRandom.setSeed(MiddleKey.getBytes());
                keyGenerator.init(128, secureRandom);
                SecretKey secretKey = keyGenerator.generateKey();
                byte[] byteKey = secretKey.getEncoded();

                // 转换KEY
                key = new SecretKeySpec(byteKey, "AES");
                return key;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return key;
        }
    }
}
