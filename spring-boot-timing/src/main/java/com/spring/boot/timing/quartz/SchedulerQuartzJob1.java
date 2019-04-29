package com.spring.boot.timing.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Dong.L
 * @Create: 2019-02-28 14:32
 * @Description: 实现Job接口
 */
public class SchedulerQuartzJob1 implements Job {

    private void before() {
        System.out.println("->> 任务开始执行: SchedulerQuartzJob1");
    }

    private void after() {
        System.out.println("->> 任务结束执行: SchedulerQuartzJob1");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        before();
        System.out.println("开始：" + System.currentTimeMillis() + " ->> " + dateFormat().format(new Date()));
        // TODO 业务
        System.out.println("结束：" + System.currentTimeMillis() + " ->> " + dateFormat().format(new Date()));
        after();
    }

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
}
