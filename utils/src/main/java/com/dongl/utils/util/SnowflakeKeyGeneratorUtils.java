/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：SnowflakeKeyGeneratorUtils.java
 * 代码说明：雪花唯一编号生成工具
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/14 17:38 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import com.dongl.utils.service.TimeService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 雪花唯一编号生成工具
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2020/1/14 17:38
 * @Author: Dong.L
 **/
@Slf4j
public class SnowflakeKeyGeneratorUtils {
    public static final long EPOCH;

    private static int maxTolerateTimeDifferenceMilliseconds = 500;
    private static TimeService timeService = new TimeService();
    private static long workerId;
    private long sequence;
    private static long initSequence;
    private long lastTime;

    public SnowflakeKeyGeneratorUtils() {
    }

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 0, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        EPOCH = calendar.getTimeInMillis();
        initSequence = RandomUtils.nextLong(0L, 60L) * 60L;
    }

    public static void setWorkerId(long workerId) {
        Preconditions.checkArgument(workerId >= 0L && workerId < 1024L);
        SnowflakeKeyGeneratorUtils.workerId = workerId;
    }

    /**
     * @param
     * @method: generateKey
     * @description: 唯一订单号生成
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/15 9:24
     */
    public synchronized long generateKey() {
        long currentMillis = timeService.getCurrentMillis();
        if (this.waitTolerateTimeDifferenceIfNeed(currentMillis)) {
            currentMillis = timeService.getCurrentMillis();
        }
        if (this.lastTime == currentMillis) {
            if (0L == (this.sequence = ++this.sequence & 4095L)) {
                currentMillis = this.waitUntilNextTime(currentMillis);
            }
        } else {
            this.sequence = initSequence;
        }
        this.lastTime = currentMillis;
        if (log.isInfoEnabled()) {
            log.info("{}-{}-{}",
                    new Object[]{DateUtils.dateToStr(new Date(this.lastTime), DateUtils.yyyy_MM_dd_HH_mm_ss_sss2),
                            workerId, this.sequence});
        }
        long key = currentMillis - EPOCH << 22 | workerId << 12 | this.sequence;
        return key;
    }

    /**
     * @param lastTime
     * @method: waitUntilNextTime
     * @description: 最后时间戳
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/14 18:09
     */
    private long waitUntilNextTime(long lastTime) {
        long time;
        for (time = timeService.getCurrentMillis(); time <= lastTime; time = timeService.getCurrentMillis()) {
        }
        return time;
    }

    /**
     * @param currentMilliseconds
     * @method: waitTolerateTimeDifferenceIfNeed
     * @description: 时间戳判断
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/14 18:07
     */
    private boolean waitTolerateTimeDifferenceIfNeed(long currentMilliseconds) throws IllegalStateException {
        if (this.lastTime <= currentMilliseconds) {
            return false;
        }
        long timeDifferenceMilliseconds = this.lastTime - currentMilliseconds;
        Preconditions.checkState(timeDifferenceMilliseconds < (long) maxTolerateTimeDifferenceMilliseconds,
                "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds",
                this.lastTime, currentMilliseconds);
        try {
            Thread.sleep(timeDifferenceMilliseconds);
        } catch (InterruptedException ex) {
        }
        return true;
    }

    public static void setTimeService(TimeService timeService) {
        SnowflakeKeyGeneratorUtils.timeService = timeService;
    }
}
