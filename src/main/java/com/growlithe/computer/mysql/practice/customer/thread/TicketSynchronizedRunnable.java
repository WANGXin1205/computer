package com.growlithe.computer.mysql.practice.customer.thread;

import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;

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
    public  void run() {
        synchronized (this.tickets){
            /*
            tickets 一定要有this才可以加锁 然后线程1执行完毕，才会释放锁，开始执行线程2
            实际上，synchronized(this)以及非static的synchronized方法（至于static synchronized方法请往下看），
            只能防止多个线程同时执行同一个对象的同步代码段。
            synchronized锁住的是代码还是对象。答案是：synchronized锁住的是括号里的对象，而不是代码。
            对于非static的synchronized方法，锁的就是对象本身也就是this。
            */
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
