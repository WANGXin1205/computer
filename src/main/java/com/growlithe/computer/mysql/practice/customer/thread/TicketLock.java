package com.growlithe.computer.mysql.practice.customer.thread;

import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author : Growlithe
 * @Date : 2018/7/2 22:23
 * @Description
 */
public class TicketLock implements Runnable {
    /**
     * 票
     */
    private Integer tickets = 50;
    /**
     * 锁
     *    Lock和synchronized有以下几点不同：
     * 　　1 Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
     * 　　2 synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，
     *        如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
     * 　　3 Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
     * 　　4 通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
     * 　　5 Lock可以提高多个线程进行读操作的效率。
     *
     *     ReentrantLock “可重入锁”  基于线程的分配，而不是基于方法调用的分配。
     *     ReadWriteLock 两个方法，一个用来获取读锁，一个用来获取写锁。也就是说将文件的读写操作分开，分成2个锁来分配给线程，
     *     从而使得多个线程可以同时进行读操作。
     *     下面的ReentrantReadWriteLock实现了ReadWriteLock接口。
     *     ReentrantReadWriteLock 主要的有两个方法：readLock()和writeLock()用来获取读锁和写锁。
     *     ReadLock是共享的，而WriteLock是独占的。于是Sync类覆盖了AQS中独占和共享模式的抽象方法(tryAcquire/tryAcquireShared等)，
     *     用同一个等待队列来维护读/写排队线程，而用一个32位int state标示和记录读/写锁重入次数--Doug Lea把状态的高16位用作读锁，
     *     记录所有读锁重入次数之和，低16位用作写锁，记录写锁重入次数。所以无论是读锁还是写锁最多只能被持有65535次。
     *
     *     StampedLock控制锁有三种模式（排它写，悲观读，乐观读），
     *     一个StampedLock状态是由版本和模式两个部分组成，锁获取方法返回一个数字作为票据stamp
     */
    private Lock lock = new ReentrantLock();

    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        TicketLock ticketLock = new TicketLock();

        Thread tr1 = new Thread(ticketLock, "窗口1");
        Thread tr2 = new Thread(ticketLock, "窗口2");
        Thread tr3 = new Thread(ticketLock, "窗口3");

        tr1.start();
        tr2.start();
        tr3.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 加锁
//                lock.lock();
                // 为了演示 加个读锁
                reentrantReadWriteLock.writeLock().lock();
                if (tickets > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()
                            + "正在出售第" + (tickets--) + "张票");
                }
            } finally {
                // 释放锁
//                lock.unlock();
                // 为了演示
                reentrantReadWriteLock.writeLock().unlock();
            }
        }
    }

}
