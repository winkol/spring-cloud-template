package com.springboot.apollo.configuration;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: Dong.L
 * @Create: 2019-09-11 14:43
 * @Description: java类描述
 */
public class PropertiesRefresher implements ApplicationContextAware {
    ApplicationContext applicationContext;

    @Autowired
    RefreshScope refreshScope;

    @ApolloConfigChangeListener
    private void someChangeHandler(ConfigChangeEvent changeEvent) {
//         boolean eurekaPropertiesChanged = false;
//         for (String changedKey : changeEvent.changedKeys()) {
//             if (changedKey.startsWith("eureka.")) {
//                 log.info("===============================================================");
//                 log.info("changedKey:{} value:{}",changedKey,changeEvent.getChange(changedKey));
//                 ConfigChange configChange = changeEvent.getChange(changedKey);
//                 configChange.getOldValue();
//                 eurekaPropertiesChanged = true;
//                 break;
//             }
//         }
        refreshProperties(changeEvent);
//        if (eurekaPropertiesChanged) {
//            refreshEurekaProperties(changeEvent);
//        }
    }

    public void refreshProperties(ConfigChangeEvent changeEvent) {
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
        refreshScope.refreshAll();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
