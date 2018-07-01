package com.growlithe.computer.mysql.practice.customer.utils;

import com.growlithe.computer.mysql.practice.customer.thread.MyCallable;
import com.growlithe.computer.mysql.practice.customer.thread.MyRunnable;
import com.growlithe.computer.mysql.practice.customer.thread.MyThread;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

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
     *
     * @param threadName
     * @return
     */
    public static MyRunnable getThreadByMyRunnable(String threadName) {
        MyRunnable myRunnable = new MyRunnable();
        myRunnable.setThreadName(threadName);
        return myRunnable;
    }

    /**
     * 获取callable
     * @param threadName
     * @return
     */
    public static MyCallable getMyCallable(String threadName) {
        return new MyCallable(threadName);
    }
    /**
     * 用 MyCallable 创建线程
     *
     * @param threadName
     * @return
     */
    public static FutureTask<Integer> getThreadByMyCallable(String threadName) {
        MyCallable myCallable = new MyCallable();
        myCallable.setThreadName(threadName);
        // FutureTask 继承了 RunnableFuture，RunnableFuture 继承了Runnable和Future
        return new FutureTask<>(myCallable);
    }

    /*
     * ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
     * 使用线程池创建线程参数太多，所以我们使用Executors 工厂类创建
     * 固定大小的线程池
     */

    /**
     * 获取线程数 等于 CPU数 的线程池
     *
     * @return
     */
    public static ExecutorService getMaxCpuNumThreadPool() {
        Integer maxCpuNum = Runtime.getRuntime().availableProcessors();
        return ThreadUtils.getFixedThreadPool(maxCpuNum);
    }

    /**
     * 获取固定大小的线程池
     *
     * @param threadNum
     * @return
     */
    public static ExecutorService getFixedThreadPool(Integer threadNum) {
        Integer maxCpuNum = Runtime.getRuntime().availableProcessors();
        if (threadNum > maxCpuNum) {
            threadNum = maxCpuNum;
        }

        return Executors.newFixedThreadPool(threadNum);
    }

    /**
     * 获取单个的线程池
     *
     * @return
     */
    public static ExecutorService getSingleThreadPool() {
        return Executors.newSingleThreadExecutor();
    }

    /**
     * 获取可缓存线程的线程池
     *
     * @return
     */
    public static ExecutorService getCachedThreadPool() {
        return Executors.newCachedThreadPool();
    }


}
