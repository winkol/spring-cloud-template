package com.dongl.utils.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @Author: Dong.L
 * @Date: 2019/11/20 11:07
 * @Description: SHA256加密算法
 */
public class SHA256Util {
    /**
     * https://blog.csdn.net/ZYK1746914945/article/details/82784174
     * Http请求加密规则（3DES、Base64、HMAC SHA256）
     */

    public static void main(String[] args) {
        System.out.println(getSHA256StrJava("10ABC"));
        System.out.println(getSHA256StrJava("ABC-123213-okfae"));
    }

    /**
     * 利用java原生的类实现SHA256加密
     *
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256StrJava(String str) {

        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
