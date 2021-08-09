///*
// * **************************************************************
// * Copyright ⓒ XIWEI PERSONAL DEVELOPMENT ,LTD.ALL
// * RIGHTS RESERVED.
// * **************************************************************
// * PROJECT INFORMATION:
// * FILENAME：ProcessTest.java
// * EXPLAIN：TODO
// * **************************************************************
// * CHANGE HISTORY:
// * Author Date Version Reason
// * Dong.L 2021/8/9 18:54 v1.0.0 初始创建
// *
// * **************************************************************
// */
//package com.xiwei.model;
//
//import org.junit.Test;
//
//import java.net.URL;
//import java.net.URLClassLoader;
//
///**
// * @Description: TODO
// * @Project: com.xiwei.model
// * @CreateDate: Created in 2021/8/9 18:54
// * @Author: Dong.L
// **/
//public class ProcessTest {
//    @Test
//    public void loaderClassTwoTest() throws Exception {
//        URL url1 = new URL("file:E:\\ld_work\\xiwei\\Projectes\\GW\\gateway_x_service\\out_jar\\sgcc-model.jar");
//        URLClassLoader myClassLoader1 = new URLClassLoader(new URL[] { url1 }, Thread.currentThread()
//                .getContextClassLoader());
//        Class<?> myClass1 = myClassLoader1.loadClass("com.xiwei.model.ModelProcessImpl");
//        IModelProcess process = (IModelProcess) myClass1.newInstance();
//        String str = process.process("v1.2");
//        System.out.println(str);
//    }
//}
