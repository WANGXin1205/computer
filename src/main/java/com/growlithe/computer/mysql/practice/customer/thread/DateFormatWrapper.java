package com.growlithe.computer.mysql.practice.customer.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author : Growlithe
 * @Date : 2018/7/2 21:40
 * @Description
 */
public class DateFormatWrapper {

//    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 主要为了演示这个 ThreadLocal
     *
     * 每个 Thread 都会拥有一个 ThreadLocalMap 变量，来存放属于该 Thread 的所有 ThreadLocal 变量。
     * 这样来看的话，ThreadLocal就相当于一个调度器，每次调用 get 方法的时候，都会先找到当前线程的 ThreadLocalMap，
     * 然后再在这个 ThreadLocalMap 中找到对应的线程本地变量。
     *
     * ThreadLocal 还提供了修改和删除当前包含对象的方法，修改的方法为 set，删除的方法为 remove：
     *
     * gc后，调用expungeStaleEntry ，这部分代码的作用就是将 i 位置上的 Entry 的 value 设置为 null，以及将 Entry 的引用设置为 null。
     */
    private static final ThreadLocal<SimpleDateFormat> SDF = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

    };

    public static String format(Date date) {
        return SDF.get().format(date);
    }

    public static Date parse(String str) throws ParseException {
        return SDF.get().parse(str);
    }
}
