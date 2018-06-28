package com.growlithe.computer.mysql.practice.customer.utils;

import com.growlithe.computer.mysql.practice.customer.thread.MyThread;
import org.springframework.stereotype.Component;

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


}
