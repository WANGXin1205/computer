package com.growlithe.computer.mysql.practice.customer.thread;

/**
 * @Author : Growlithe
 * @Date : 2018/6/28 21:56
 * @Description
 */
public class MyRunnable implements Runnable {

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
