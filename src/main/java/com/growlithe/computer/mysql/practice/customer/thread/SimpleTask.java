package com.growlithe.computer.mysql.practice.customer.thread;

import java.sql.Time;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @Author : Growlithe
 * @Date : 2018/7/1 21:22
 * @Description
 */
public class SimpleTask implements Callable<Double> {

    private final int sleepTime;

    public SimpleTask(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public Double call() throws Exception {
        double begin = System.nanoTime();

        // Thread.sleep 方法是可以对中断做出响应的
        Thread.sleep(sleepTime);

        double end = System.nanoTime();
        // 返回任务运行的时间，以 秒 计
        double time = (end - begin) / 1E9;

        return time;
    }

}
