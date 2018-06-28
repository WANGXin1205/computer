package com.growlithe.computer.mysql.practice.customer.thread;

/**
 * @Author : Growlithe
 * @Date : 2018/6/28 21:56
 * @Description
 */
public class MyRunnable implements Runnable {

    private String threadName;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i=0; i<20;i++){
            System.out.println("this is   " + threadName +" " + i);
        }
    }


}
