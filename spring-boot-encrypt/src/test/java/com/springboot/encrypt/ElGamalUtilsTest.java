package com.springboot.encrypt;

import com.springboot.encrypt.util.ElGamalUtils;
import org.junit.Test;

/**
 * @ClassName: ElGamalUtilsTest
 * @Description: 基于 ElGamal 算法的非对称加密工具的测试类
 * @Author: Dong.L
 * @Create: 2019/12/18 10:40
 */
public class ElGamalUtilsTest {

    static final String STR = "Hello Dong.L";

    @Test
    public void bcElGamal() {
        ElGamalUtils.bcElGamal(STR);
    }
}