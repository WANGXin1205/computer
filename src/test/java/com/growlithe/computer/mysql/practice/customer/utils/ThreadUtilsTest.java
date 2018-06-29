package com.growlithe.computer.mysql.practice.customer.utils;

import com.growlithe.computer.mysql.practice.customer.thread.MyRunnable;
import com.growlithe.computer.mysql.practice.customer.thread.MyThread;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import static org.junit.Assert.*;

/**
 * @Author : Growlithe
 * @Date : 2018/6/29 0:12
 * @Description
 */
public class ThreadUtilsTest {

    /**
     * 如果用单元测试，不进行依赖注入，会造成打印失效，还是用main方法直观
     */
    private static final  String threadName = "first thread";
    private static final  String threadName1 = "second thread";

    @Before
    private static void myThreadStart() {
        MyThread myThread = ThreadUtils.getThreadByMyThread(threadName);
        MyThread myThread1 = ThreadUtils.getThreadByMyThread(threadName1);
        myThread.start();
        myThread1.start();
    }

    @Before
    private static void myRunnableStart(){
        MyRunnable myRunnable = ThreadUtils.getThreadByMyRunnable(threadName);
        MyRunnable myRunnable1 = ThreadUtils.getThreadByMyRunnable(threadName1);
        // 这里使用了new Thread的两种构造方法，但是 java 不建议用new Thread 方法创建线程，而要用工厂类或者Executors创建线程
        new Thread(myRunnable).start();
        new Thread(myRunnable1,myRunnable1.getThreadName()).start();
    }

    @Before
    private static void futureTaskStart(){
        FutureTask<Integer> futureTask = ThreadUtils.getThreadByMyCallable(threadName);
        FutureTask<Integer> futureTask1 = ThreadUtils.getThreadByMyCallable(threadName1);
        new Thread(futureTask).start();
        new Thread(futureTask1).start();
    }

    public static void main(String args[]) {
//        ThreadUtilsTest.myThreadStart();
//        ThreadUtilsTest.myRunnableStart();
        ThreadUtilsTest.futureTaskStart();
    }


}