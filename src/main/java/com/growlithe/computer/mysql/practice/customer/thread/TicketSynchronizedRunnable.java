package com.growlithe.computer.mysql.practice.customer.thread;

import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.ThreadFactory;

/**
 * @Author : Growlithe
 * @Date : 2018/7/2 15:15
 * @Description
 */
public class TicketSynchronizedRunnable implements Runnable {

    private Integer tickets = 100;

    private String threadName;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public String toString() {
        return "TicketSynchronizedRunnable{" +
                "threadName='" + threadName + '\'' +
                '}';
    }

    /**
     * 也可以这么写 public synchronized void run(){}
     */
    @Override
    public void run() {
        synchronized (this){
            // tickets 一定要有this才可以加锁
            while (this.tickets > 0) {
                System.out.println("线程" + threadName + "销售出一张票，剩余： " + --this.tickets);
            }
        }
    }


    public static void main(String[] args) {
        TicketSynchronizedRunnable ticketSynchronizedRunnable = new TicketSynchronizedRunnable();
        ticketSynchronizedRunnable.setThreadName("one");
        TicketSynchronizedRunnable ticketSynchronizedRunnable1 = new TicketSynchronizedRunnable();
        ticketSynchronizedRunnable1.setThreadName("two");

        ThreadFactory threadFactory = new ThreadPoolExecutorFactoryBean();
        threadFactory.newThread(ticketSynchronizedRunnable).start();
        threadFactory.newThread(ticketSynchronizedRunnable1).start();
    }
}
