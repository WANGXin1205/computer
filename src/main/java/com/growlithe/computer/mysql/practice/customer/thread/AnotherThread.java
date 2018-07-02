package com.growlithe.computer.mysql.practice.customer.thread;

/**
 * @Author : Growlithe
 * @Date : 2018/7/2 14:48
 * @Description
 */
public class AnotherThread extends Thread {

    /**
     * 不加volatile会造成死锁，
     * volatile 作为 Java 语言的一个关键字，被看作是轻量级的 synchronized（锁）。
     * 虽然 volatile 只具有synchronized 的部分功能，但是一般使用 volatile 会比使用 synchronized 更有效率。
     * 在编写多线程程序的时候，volatile 修饰的变量能够：
     * 1.保证内存 可见性 线程A对一个volatile变量的修改，对于其它线程来说是可见的，即线程每次获取volatile变量的值都是最新的。
     * 2.防止指令 重排序
     * 3.保证对 64 位变量 读写的原子性 long 或 double 的读写并不是原子性的，
     *   这样在并发程序中共享 long 或 double 变量就可能会出现问题，于是 JVM 提供了 volatile 关键字来解决这个问题
     * <p>
     * 对于 volatile 修饰的变量，JVM 可以保证：
     * <p>
     * 1.每次对该变量的写操作，都将立即同步到主存；
     * 2.每次对该变量的读操作，都将从主存读取，而不是线程栈
     * <p>
     * 通过关键字sychronize可以防止多个线程进入同一段代码，在某些特定场景中，volatile相当于一个轻量级的sychronize，因为不会引起线程的上下文切换，但是使用volatile必须满足两个条件：
     * <p>
     * 1、对变量的写操作不依赖当前值，如多线程下执行a++，是无法通过volatile保证结果准确性的;
     * <p>
     * 2、该变量没有包含在具有其它变量的不变式中，这句话有点拗口，看代码比较直观。
     */
    private static volatile boolean running = true;

    @Override
    public void run() {
        System.out.println("AnotherThread is running");

        while (running) {
        }

        System.out.println("AnotherThread is stoped");
    }


    public static void main(String[] args) throws Exception {
        new AnotherThread().start();

        Thread.sleep(1000);
        // 1 秒之后想停止 AnotherThread
        running = false;
    }

}
