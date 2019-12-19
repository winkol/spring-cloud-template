package com.springboot.encrypt;

import com.springboot.encrypt.util.Base64Utils;
import org.junit.Test;

/**
 * @ClassName: Base64UtilsTest
 * @Description: Base64 加密工具的测试类
 * @Author: Dong.L
 * @Create: 2019/12/18 9:58
 */
public class Base64UtilsTest {

    static final String str = "Hello Dong.L";

    @Test
    public void jdkBase64() {
        System.out.println("JDK Base64: " + Base64Utils.jdkBase64(str));
    }

    @Test
    public void commonsCodecBase64() {
        System.out.println("CC  Base64: " + Base64Utils.commonsCodecBase64(str));
    }

    @Test
    public void bouncyCastleBase64() {
        System.out.println("BC  Base64: " + Base64Utils.bouncyCastleBase64(str));
    }
}