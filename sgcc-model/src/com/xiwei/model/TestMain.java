/*
 * **************************************************************
 * Copyright ⓒ XIWEI PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * FILENAME：TestMain.java
 * EXPLAIN：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2021/8/9 18:31 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.xiwei.model;

/**
 * @Description: TODO
 * @Project: com.xiwei.model
 * @CreateDate: Created in 2021/8/9 18:31
 * @Author: Dong.L
 **/
public class TestMain {
    public static void main(String[] args) {
        IModelProcess process = new ModelProcessImpl();
        System.out.println(process.process("v1.0"));
    }
}
