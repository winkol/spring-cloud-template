/*
 * **************************************************************
 * Copyright ⓒ XIWEI PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * FILENAME：ModelProcessImpl.java
 * EXPLAIN：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2021/8/9 18:15 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.xiwei.model;

/**
 * @Description: TODO
 * @Project: com.xiwei.model
 * @CreateDate: Created in 2021/8/9 18:15
 * @Author: Dong.L
 **/
public class ModelProcessImpl implements IModelProcess {
    @Override
    public String process(String content) {
        return String.format("%s%s", "这里是模型加工：", content);
    }
}
