package com.springboot.encrypt;

import com.springboot.encrypt.util.DhUtils;
import org.junit.Test;

/**
 * @ClassName: DhUtilsTest
 * @Description: 基于 DH 算法的非对称加密工具的测试类
 * @Author: Dong.L
 * @Create: 2019/12/18 10:24
 */
public class DhUtilsTest {

    static final String STR = "Hello Dong.L";

    @Test
    public void jdkDH() {
        DhUtils.jdkDH(STR);
    }
}