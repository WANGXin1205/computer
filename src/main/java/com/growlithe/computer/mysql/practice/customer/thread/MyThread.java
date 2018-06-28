package com.growlithe.computer.mysql.practice.customer.thread;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author : Growlithe
 * @Date : 2018/6/28 21:49
 * @Description
 */
public class MyThread extends Thread {

    private static final Integer MAX_TIME = 50;

    private Integer tickets;

    private String threadName;

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
    public void run() {
        if (tickets == null) {
            for (int i = 1; i < MAX_TIME + 1; i++) {
                System.out.println("this is " + threadName + " " + i);
            }
        }
        if (tickets != null) {
            for (int i = tickets; i > 0; i--) {
                System.out.println("this is " + threadName + " " + i);
            }
        }
    }

}
