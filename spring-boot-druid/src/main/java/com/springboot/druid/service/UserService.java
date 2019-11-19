package com.springboot.druid.service;

import com.springboot.druid.annotation.ServiceLogs;
import com.springboot.druid.exception.ServiceException;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

/**
 * @Author: Dong.L
 * @Create: 2019-11-17 16:25
 * @Description: java类描述
 */
@Service
public class UserService {

    @ApiOperation("测试异常，逻辑层")
    @ServiceLogs
    public String testException(Long id) {
        if (id > 0) {
            throw new ServiceException("逻辑层，异常测试。");
        }
        return "ok";
    }
}
