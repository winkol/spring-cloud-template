package com.cloud.config.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Dong.L
 * @Create: 2019-01-15 17:52
 * @Description: java类描述
 */
@RestController
@RefreshScope // 动态刷新
public class HelloController {
    @Autowired
    private Environment environment;
    @Autowired
    private DiscoveryClient client;
    @Autowired
    private Registration registration;

    /**
     * git配置文件里的key
     */
    @Value("${mytest}")
    String mytest;

    @RequestMapping(value = "/hi")
    public String hi() {
        return mytest;
    }

    @RequestMapping("/hello")
    public String hello(){
        return environment.getProperty("mytest", "未定义");
    }
    @RequestMapping(value = "/add/{a}/{b}",method = RequestMethod.GET)
    public String add(@PathVariable Integer a, @PathVariable Integer b)
    {
        System.out.println("host:"+ serviceInstance().getHost()+"   -----port:"+serviceInstance().getPort());

        String result = "/testBalance, host:port=" + serviceInstance().getUri()  + ", "
                + "service_id:" + serviceInstance().getServiceId();
        System.out.println(result);
        return a+b+" ------      host:"+serviceInstance().getHost()+"   -----port:"+serviceInstance().getPort()
                + "\n" + result;
    }

    public ServiceInstance serviceInstance() {
        List<ServiceInstance> list = client.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            for(ServiceInstance itm : list){
                if(itm.getPort() == 7002 || itm.getPort() == 7003) {
                    return itm;
                }
            }
        }
        return null;
    }

}
