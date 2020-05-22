/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：DownloadHandler.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/20 17:22 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.pattern.handler;

import com.google.common.collect.ImmutableMap;
import com.springboot.pattern.annotation.ReqType;
import com.springboot.pattern.enums.ReqTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: TODO
 * @Project: com.springboot.pattern.handler
 * @CreateDate: Created in 2020/5/20 17:22
 * @Author: Dong.L
 **/
@Slf4j
@Component
@ReqType(ReqTypeEnum.DOWNLOAD)
public class DownloadHandler extends AbstractReqTypeHandler {
    @Override
    public Map<String, Object> handler(String reqType) {
        log.info("->> reqType: {}", reqType);
        return ImmutableMap.of("reqType", reqType);
    }
}
