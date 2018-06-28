package com.growlithe.computer.mysql.practice.customer.utils;

import com.growlithe.computer.mysql.practice.customer.thread.MyThread;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    @Before
    private static void myThreadStart() {
        String threadName = "first thread";
        String threadName1 = "second thread";
        MyThread myThread = ThreadUtils.getThreadByMyThread(threadName);
        MyThread myThread1 = ThreadUtils.getThreadByMyThread(threadName1);
        myThread.start();
        myThread1.start();
    }


    public static void main(String args[]) {
        ThreadUtilsTest.myThreadStart();
    }


}