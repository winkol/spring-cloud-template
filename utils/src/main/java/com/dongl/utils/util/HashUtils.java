/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：HashUtils.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/13 14:58 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @Description: 处理hash工具类
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2020/1/13 14:58
 * @Author: Dong.L
 **/
public class HashUtils {

    /**
     * @param key
     * @method: hash
     * @description: murmur hash算法
     * @return: long
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/13 15:06
     */
    public static long hash(byte[] key) {
        ByteBuffer buf = ByteBuffer.wrap(key);
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }

    /**
     * @param key
     * @method: hash
     * @description: murmur hash算法
     * @return: long
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/13 15:06
     */
    public static long hash(String key) {
        return hash(key.getBytes());
    }

    /**
     * @param value
     * @method: readUnsignedLong
     * @description: Long转换成无符号长整型（C中数据类型）
     * @return: BigDecimal
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/13 15:11
     */
    public static BigDecimal readUnsignedLong(long value) {
        if (value > 0) {
            return new BigDecimal(value);
        }
        long lowValue = value & Long.MAX_VALUE;
        return BigDecimal.valueOf(lowValue).add(BigDecimal.valueOf(Long.MAX_VALUE)).add(BigDecimal.valueOf(1));
    }

    /**
     * @param key
     * @method: hashUnsigned
     * @description: 无符号murmur hash值
     * @return: BigDecimal
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/13 15:12
     */
    public static BigDecimal hashUnsigned(String key) {
        return readUnsignedLong(hash(key));
    }

    /**
     * @param key
     * @method: hashUnsigned
     * @description: 无符号murmur hash值
     * @return: BigDecimal
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/13 15:12
     */
    public static BigDecimal hashUnsigned(byte[] key) {
        return readUnsignedLong(hash(key));
    }
}
