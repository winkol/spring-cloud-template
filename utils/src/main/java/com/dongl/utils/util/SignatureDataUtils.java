/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：SignatureDataUtils.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/13 15:46 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 数字签名-编写发送者的功能：首先通过私钥加密待输出数据Data，并输出Data和签名后的Data
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2020/1/13 15:46
 * @Author: Dong.L
 **/
public class SignatureDataUtils {
    /**
     * 私key
     */
    private static String priKey = "pri_key";
    /**
     * 公key
     */
    private static String pubKey = "pub_key";
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    /**
     * RSA最大加密明文大小
     */
    public static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    public static final int MAX_DECRYPT_BLOCK = 117;

    /**
     * @param seedValue 随机参数
     * @method: getKeyPair
     * @description: 生成公私钥对
     * @return: Map
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/13 16:12
     */
    public static Map<String, String> getKeyPair(String seedValue) {
        Map<String, String> keyMap = new HashMap<>(2);
        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom secrand = new SecureRandom();
            // 初始化随机产生器
            secrand.setSeed(seedValue.getBytes());
            keygen.initialize(1024, secrand);
            KeyPair keys = keygen.genKeyPair();

            PublicKey pubkey = keys.getPublic();
            PrivateKey prikey = keys.getPrivate();

            keyMap.put(pubKey, bytesToHexStr(pubkey.getEncoded()));
            keyMap.put(priKey, bytesToHexStr(prikey.getEncoded()));

            System.out.println("pubKey=" + keyMap.get(pubKey));
            System.out.println("priKey=" + keyMap.get(priKey));

            System.out.println("写入对象 pubkeys ok");
            System.out.println("生成密钥对成功");
            return keyMap;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成密钥对失败");
        }
        return null;
    }

    /**
     * @param data   要验证的信息
     * @param signed 签名
     * @param pubKey 公钥
     * @method: verifySign
     * @description: 验证签名
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/15 16:12
     */
    public static boolean verifySign(String data, String signed, String pubKey) {
        try {
            if (signed.length() != 256) {
                return false;
            }
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(hexStrToBytes(pubKey));
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(bobPubKeySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            // 用公钥校验签名是否正常
            signature.initVerify(publicKey);
            signature.update(data.getBytes("ISO-8859-1"));
            if (signature.verify(hexStrToBytes(signed))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * @param prikeyvalue 私钥
     * @param myinfo      要签名的信息
     * @method: getSignPri
     * @description: 私钥生成签名
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/15 16:07
     */
    public static String getSignPri(String prikeyvalue, String myinfo) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(hexStrToBytes(prikeyvalue));
            KeyFactory keyf = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);

            // 用私钥对信息生成数字签名
            Signature signet = Signature.getInstance(SIGNATURE_ALGORITHM);
            signet.initSign(myprikey);
            signet.update(myinfo.getBytes("ISO-8859-1"));
            // 对信息的数字签名
            byte[] signed = signet.sign();

            String sign = bytesToHexStr(signed);
            System.out.println("signed(签名内容)原值=" + sign);
            System.out.println("info（原值）=" + myinfo);

            System.out.println("签名并生成文件成功");
            return sign;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            System.out.println("签名并生成文件失败");
        }
        return null;
    }

    /**
     * @param data   源数据
     * @param pubKey 公钥
     * @method: encryptByPublicKey
     * @description: 公钥加密
     * @return:
     * @throws: Exception
     * @author: Dong.L
     * @date: 2020/1/15 16:37
     */
    public static byte[] encryptByPublicKey(byte[] data, String pubKey) throws Exception {
        byte[] keyBytes = hexStrToBytes(pubKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * @param data   已加密数据
     * @param priKey 私钥
     * @method: encryptByPrivateKey
     * @description: 私钥加密
     * @return:
     * @throws: Exception
     * @author: Dong.L
     * @date: 2020/1/15 16:36
     */
    public static byte[] encryptByPrivateKey(byte[] data, String priKey) throws Exception {
        byte[] keyBytes = hexStrToBytes(priKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * Transform the specified byte into a Hex String form.
     */
    public static final String bytesToHexStr(byte[] bcd) {
        StringBuffer s = new StringBuffer(bcd.length * 2);

        for (int i = 0; i < bcd.length; i++) {
            s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
            s.append(bcdLookup[bcd[i] & 0x0f]);
        }

        return s.toString();
    }

    /**
     * Transform the specified Hex String into a byte array.
     */
    public static final byte[] hexStrToBytes(String s) {
        byte[] bytes;

        bytes = new byte[s.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;
    }

    private static final char[] bcdLookup = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f'};
}
