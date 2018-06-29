package com.growlithe.computer.mysql.practice.customer.utils;

import com.growlithe.computer.mysql.practice.customer.thread.MyCallable;
import com.growlithe.computer.mysql.practice.customer.thread.MyRunnable;
import com.growlithe.computer.mysql.practice.customer.thread.MyThread;
import org.springframework.stereotype.Component;

import java.util.concurrent.FutureTask;

/**
 * @Author : Growlithe
 * @Date : 2018/6/28 23:53
 * @Description
 */
public class ThreadUtils {

    /**
     * 用 MyThread 创建线程
     */
    public static MyThread getThreadByMyThread(String threadName) {
        MyThread myThread = new MyThread();
        myThread.setThreadName(threadName);
        return myThread;
    }

    /**
     * 用 MyRunnable 创建线程
     * @param threadName
     * @return
     */
    public static MyRunnable getThreadByMyRunnable(String threadName){
        MyRunnable myRunnable = new MyRunnable();
        myRunnable.setThreadName(threadName);
        return myRunnable;
    }

    /**
     * 用 MyCallable 创建线程
     * @param threadName
     * @return
     */
    public static FutureTask<Integer> getThreadByMyCallable(String threadName){
        MyCallable myCallable = new MyCallable();
        myCallable.setThreadName(threadName);
        // FutureTask 继承了 RunnableFuture，RunnableFuture 继承了Runnable和Future
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        return futureTask;
    }

}
