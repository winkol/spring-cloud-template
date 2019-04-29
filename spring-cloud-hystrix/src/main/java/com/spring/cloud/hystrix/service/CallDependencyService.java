package com.spring.cloud.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author: Dong.L
 * @Create: 2019-02-20 17:39
 * @Description: 调用依赖服务，通过hystrix包装调用服务
 */
@Component
public class CallDependencyService {
    private Random random = new Random();

    /**
     * 模拟获取用户信息(通过网络调用)
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public String mockGetUserInfo(){
        int randomInt= random.nextInt(10) ;
        //模拟调用失败情况
        if(randomInt<8){
            throw new RuntimeException("call dependency service fail.");
        }else{
            return "UserName:liaokailin;number:"+randomInt;
        }
    }

    public String fallback(){
        return "some exception occur call fallback method.";
    }
}
