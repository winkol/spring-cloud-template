package com.springboot.apollo.util;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;

import java.util.Properties;
import java.util.Set;

/**
 * @Author: Dong.L
 * @Create: 2019-10-31 10:47
 * @Description: apollo中的值发生改变，覆盖PropertiesUtils中键值
 */
public class ApolloConfigurationChange {
    public static void monitorApolloConfigurationChange(Properties properties, Config config) {
        config.addChangeListener(configChangeEvent -> {
            Set<String> keys = configChangeEvent.changedKeys();
            for (String key : keys) {
                ConfigChange configChange = configChangeEvent.getChange(key);
                //覆盖旧值
                PropertiesUtilsT.properties.setProperty(key, configChange.getNewValue());
                System.out.println(configChange.getPropertyName() + " 的值改变了，原值：" +
                        configChange.getOldValue() + ",新值：" + configChange.getNewValue());
            }
        });
    }
}
