package com.springboot.encrypt.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @ClassName: Base64Utils
 * @Description: Base64 加密工具类
 * @Author: Dong.L
 * @Create: 2019/12/18 10:22
 */
public class Base64Utils {

    /**
     * 使用 JDK Base64 加密
     *
     * @param str 明文
     * @return Base64 密文
     */
    public static String jdkBase64(String str) {
        String encoder = new BASE64Encoder().encode(str.getBytes());
        try {
            System.out.println(new String(new BASE64Decoder().decodeBuffer(encoder)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encoder;
    }

    /**
     * 使用 Commons Codec Base64 加密
     *
     * @param str 明文
     * @return Base64 密文
     */
    public static String commonsCodecBase64(String str) {
        byte[] encoder = Base64.encodeBase64(str.getBytes());
        System.out.println(new String(Base64.decodeBase64(encoder)));
        return new String(encoder);
    }

    /**
     * 使用 Bouncy Castle Base64 加密
     *
     * @param str 明文
     * @return Base64 密文
     */
    public static String bouncyCastleBase64(String str) {
        byte[] encoder = org.bouncycastle.util.encoders.Base64.encode(str.getBytes());
        System.out.println(new String(org.bouncycastle.util.encoders.Base64.decode(encoder)));
        return new String(encoder);
    }
}
