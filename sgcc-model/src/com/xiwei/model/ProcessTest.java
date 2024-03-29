/*
 * **************************************************************
 * Copyright ⓒ XIWEI PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * FILENAME：ProcessTest.java
 * EXPLAIN：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2021/8/9 18:54 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.xiwei.model;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Description: jar动态加载
 *  java动态加载jar包，并运行其中的类和方法
 *  RUL：https://blog.csdn.net/wawmg/article/details/17961815
 *  java动态加载jar文件并执行方法
 *  URL：https://blog.csdn.net/zhangge3663/article/details/116331288
 * @Project: com.xiwei.model
 * @CreateDate: Created in 2021/8/9 18:54
 * @Author: Dong.L
 **/
public class ProcessTest {
    public static void main(String[] args) throws Exception {
//        loaderClassTwoTest();
        testLoadClass();
    }
    public static void loaderClassTwoTest() throws Exception {
        URL url = new URL("file:E:\\ld_work\\workspaces\\ideaIU\\spring-cloud-template\\out\\artifacts\\sgcc_model_jar\\sgcc-model.jar");
        URLClassLoader myClassLoader1 = new URLClassLoader(new URL[] { url }, Thread.currentThread()
                .getContextClassLoader());
        Class<?> myClass1 = myClassLoader1.loadClass("com.xiwei.model.ModelProcessImpl");
        IModelProcess process = (IModelProcess) myClass1.newInstance();
        String str = process.process("v1.1");
        System.out.println(str);
    }

    /**
     * java动态加载jar文件并执行方法
     * URL：https://blog.csdn.net/zhangge3663/article/details/116331288
     * @throws Exception
     */
    public static void testLoadClass() throws Exception{
        String libPath ="E:\\ld_work\\workspaces\\ideaIU\\spring-cloud-template\\out\\artifacts\\sgcc_model_jar\\sgcc-model.jar";

        loadJar(libPath);
        Class<?> aClass = Class.forName("com.xiwei.model.ModelProcessImpl");
        Object instance = aClass.newInstance();
        Object strip = aClass.getDeclaredMethod("process", String.class).invoke(instance, "v20210811.0");
        System.out.println(strip);
    }
    public static void loadJar(String jarPath) {
        File jarFile = new File(jarPath);
        // 从URLClassLoader类中获取类所在文件夹的方法，jar也可以认为是一个文件夹
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }
        //获取方法的访问权限以便写回
        boolean accessible = method.isAccessible();
        try {
            method.setAccessible(true);
            // 获取系统类加载器
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            URL url = jarFile.toURI().toURL();
            method.invoke(classLoader, url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.setAccessible(accessible);
        }
    }
}
