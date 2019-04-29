package com.spring.cloud.hystrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Dong.L
 * @Create: 2019-02-20 17:38
 * @Description: 依赖服务
 */
@Service
public class HystrixService {
    @Autowired
    private CallDependencyService dependencyService;

    public String callDependencyService() {
        return dependencyService.mockGetUserInfo();
    }
}
