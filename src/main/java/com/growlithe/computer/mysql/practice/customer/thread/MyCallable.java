package com.growlithe.computer.mysql.practice.customer.thread;

import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;

/**
 * @Author : Growlithe
 * @Date : 2018/6/28 22:48
 * @Description
 */
public class MyCallable implements Callable<Integer> {


    private static final Integer MAX_TIME = 50;

    private String threadName;

    private Integer tickets;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    @Override
    public Integer call() {
        Integer i = 0;

        if (tickets == null) {
            for (i = 1; i < MAX_TIME + 1; i++) {
                System.out.println("this is " + threadName + " " + i);
            }
        }
        if (tickets != null) {
            for (i = tickets; i > 0; i--) {
                System.out.println("this is " + threadName + " " + i);
            }
        }

        return i;
    }

}
