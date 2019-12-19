package com.springboot.encrypt;

import com.springboot.encrypt.util.SymmetricalEncryptionUtils;
import org.junit.Test;

/**
 * @ClassName: SymmetricalEncryptionUtilsTest
 * @Description: 对称加密工具的测试类
 * @Author: Dong.L
 * @Create: 2019/12/18 9:51
 */
public class SymmetricalEncryptionUtilsTest {

    static final String str = "Hello Dong.L";
    static final String content = "{'repairPhone':'18547854787','customPhone':'12365478965','captchav':'58m7'}";

    @Test
    public void jdkDES() {
        System.out.println("JDK DES Encrypt: " + SymmetricalEncryptionUtils.jdkDES(str));
    }

    @Test
    public void bcDES() {
        System.out.println("BC DES Encrypt: " + SymmetricalEncryptionUtils.bcDES(str));
    }

    @Test
    public void jdk3DES() {
        System.out.println("JDK 3DES Encrypt: " + SymmetricalEncryptionUtils.jdk3DES(str));
    }

    @Test
    public void bc3DES() {
        System.out.println("BC 3DES Encrypt: " + SymmetricalEncryptionUtils.bc3DES(str));
    }

    @Test
    public void jdkAES() {
        System.out.println("JDK DES Encrypt: " + SymmetricalEncryptionUtils.jdkAES(content));
    }

    @Test
    public void bcAES() {
        System.out.println("BC DES Encrypt: " + SymmetricalEncryptionUtils.bcAES(content));
    }

    @Test
    public void jdkPBE() {
        System.out.println("JDK PBE Encrypt: " + SymmetricalEncryptionUtils.jdkPBE(str));
    }

    @Test
    public void bcPBE() {
        System.out.println("BC PBE Encrypt: " + SymmetricalEncryptionUtils.bcPBE(str));
    }
}