package com.springboot.encrypt;

import com.springboot.encrypt.util.SignUtils;
import org.junit.Test;

/**
 * 数字签名工具的测试类
 *
 * @Author LeifChen
 * @Date 2018-12-03
 */
public class SignUtilsTest {

    @Test
    public void jdkRSA() {
        SignUtils.jdkRSA();
    }

    @Test
    public void jdkDSA() {
        SignUtils.jdkDSA();
    }

    @Test
    public void jdkECDSA() {
        SignUtils.jdkECDSA();
    }
}