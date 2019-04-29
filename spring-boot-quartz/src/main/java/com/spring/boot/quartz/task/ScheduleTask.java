package com.spring.boot.quartz.task;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 调度的任务。
 * <p>
 * testScheduleTask 字符串名称在 quartz.xml 中配置为属性 targetObject 的 value 值。</li>
 * sayHello 方法名称在 quartz.xml 中配置为属性 targetMethod 的 value 值。</li>
 *
 * @author hmilyylimh
 * @version 0.0.1
 * @date 2017/9/18
 */
@Configuration
@Component("testScheduleTask")
@EnableScheduling
public class ScheduleTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);

    public void sayHello(JobExecutionContext context) {
        //打印当前的时间
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("current time is:" + sf.format(date));
        String from = context.getJobDetail().getJobDataMap().getString("from");
        log.info("->> from: " + from);
    }
}