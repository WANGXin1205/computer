package com.growlithe.computer.mysql.practice.customer.thread;

import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.ThreadFactory;

/**
 * @Author : Growlithe
 * @Date : 2018/7/2 15:01
 * @Description
 */
public class TicketRunnable implements Runnable {

    private String threadName;

    /**
     * 这里不加static，则不会共享资源
     */
    private static Integer tickets = 100;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public TicketRunnable() {
    }

    public TicketRunnable(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (tickets > 0) {
            try {
                /*
                sleep()属于Thread类,而wait()属于Object类中的。
                sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，但是他的监控状态依然保持者，
                当指定的时间到了又会自动恢复运行状态,在调用sleep()方法的过程中，线程不会释放对象锁。
                而当调用wait()方法的时候，线程会放弃对象锁，进入等待此对象的等待锁定池，
                只有针对此对象调用notify()方法后本线程才进入对象锁定池准备
                 */
                // 这里只是为了演示sleep
                Thread.sleep(10);
            }catch (Exception e){
                System.out.println(e);
            }
            System.out.println("线程" + threadName + "销售出一张票，剩余： " + --tickets);
        }
    }

    @Override
    public String toString() {
        return "TicketRunnable{" +
                "threadName='" + threadName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        TicketRunnable ticketRunnable = new TicketRunnable("one");
        TicketRunnable ticketRunnable1 = new TicketRunnable("two");

        ThreadFactory threadFactory = new ThreadPoolExecutorFactoryBean();
        Thread thread = threadFactory.newThread(ticketRunnable);
        // 设置线程的优先级
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        threadFactory.newThread(ticketRunnable1).start();
    }
}

