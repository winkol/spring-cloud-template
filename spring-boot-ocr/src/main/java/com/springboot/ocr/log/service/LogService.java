package com.springboot.ocr.log.service;

import com.springboot.ocr.log.entity.Log;

import java.util.List;

/**
 * @Package: com.wzy.log.service
 * @Author: Clarence1
 * @Date: 2019/9/17 15:55
 */
public interface LogService {

    /**
     * 添加日志
     */
    int insertLog(Log log);

    /**
     * 通过用户ID查询日志记录
     * @param userId
     * @return
     */
    List<Log> getLogsByUserId(Long userId);

}
