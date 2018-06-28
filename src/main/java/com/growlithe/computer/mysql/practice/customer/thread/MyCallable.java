package com.growlithe.computer.mysql.practice.customer.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author : Growlithe
 * @Date : 2018/6/28 22:48
 * @Description
 */
public class MyCallable implements Callable<Integer> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer call() {
        Integer integer = 0;

        for (int i = integer; i <= 100; i++) {
            System.out.println(name+ "    " +i);
        }

        return integer;
    }


    public void start(){
        MyCallable td = new MyCallable();
        td.setName(this.name);
        //1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<Integer> futureTask = new FutureTask<>(td);

        new Thread(futureTask).start();
    }
}
