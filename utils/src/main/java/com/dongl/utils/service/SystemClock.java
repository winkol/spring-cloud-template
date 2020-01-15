/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：SystemClock.java
 * 代码说明：系统时钟类
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/14 17:42 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.service;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 系统时钟类
 * @Project: com.dongl.utils.service
 * @CreateDate: Created in 2020/1/14 17:42
 * @Author: Dong.L
 **/
public class SystemClock {
    /**
     * 预处理ID
     */
    private final long period;
    /**
     * 原子对象
     */
    private final AtomicLong now;

    private SystemClock(long period) {
        this.period = period;
        this.now = new AtomicLong(System.currentTimeMillis());
        this.scheduleClockUpdating();
    }

    public static SystemClock getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * @param
     * @method: scheduleClockUpdating
     * @description: 更新时间
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/14 17:53
     */
    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable runnable) {
                Thread thread = new Thread(runnable, "System Clock");
                thread.setDaemon(true);
                return thread;
            }
        });
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                SystemClock.this.now.set(System.currentTimeMillis());
            }
        }, this.period, this.period, TimeUnit.MILLISECONDS);
    }

    private long currentTimeMillis() {
        return this.now.get();
    }

    public static long now() {
        return getInstance().currentTimeMillis();
    }

    public static String nowDate() {
        return (new Timestamp(getInstance().currentTimeMillis())).toString();
    }

    private static class InstanceHolder {
        public static final SystemClock INSTANCE = new SystemClock(1L);

        private InstanceHolder() {
        }
    }
}
