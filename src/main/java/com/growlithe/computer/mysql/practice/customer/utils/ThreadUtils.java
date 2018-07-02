package com.growlithe.computer.mysql.practice.customer.utils;

import com.growlithe.computer.mysql.practice.customer.thread.MyCallable;
import com.growlithe.computer.mysql.practice.customer.thread.MyRunnable;
import com.growlithe.computer.mysql.practice.customer.thread.MyThread;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.concurrent.*;

/**
 * @Author : Growlithe
 * @Date : 2018/6/28 23:53
 * @Description
 */
public class ThreadUtils {

    /**
     * 用 MyThread 创建线程
     */
    public static MyThread getThreadByMyThread(String threadName) {
        MyThread myThread = new MyThread();
        myThread.setThreadName(threadName);
        return myThread;
    }

    /**
     * 用 MyRunnable 创建线程
     *
     * @param threadName
     * @return
     */
    public static MyRunnable getThreadByMyRunnable(String threadName) {
        MyRunnable myRunnable = new MyRunnable();
        myRunnable.setThreadName(threadName);
        return myRunnable;
    }

    /**
     * 获取callable
     *
     * @param threadName
     * @return
     */
    public static MyCallable getMyCallable(String threadName) {
        return new MyCallable(threadName);
    }

    /**
     * 用 MyCallable 创建线程
     *
     * @param threadName
     * @return
     */
    public static FutureTask<Integer> getThreadByMyCallable(String threadName) {
        MyCallable myCallable = new MyCallable();
        myCallable.setThreadName(threadName);
        // FutureTask 继承了 RunnableFuture，RunnableFuture 继承了Runnable和Future
        return new FutureTask<>(myCallable);
    }

    /*
     * ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
     * 使用线程池创建线程参数太多，所以我们使用Executors 工厂类创建
     * 固定大小的线程池
     */

    /**
     * 获取线程数 等于 CPU数 的线程池
     *
     * @return
     */
    public static ExecutorService getMaxCpuNumThreadPool() {
        Integer maxCpuNum = Runtime.getRuntime().availableProcessors();
        return ThreadUtils.getFixedThreadPool(maxCpuNum);
    }

    /**
     * 获取固定大小的线程池
     *
     * @param threadNum
     * @return
     */
    public static ExecutorService getFixedThreadPool(Integer threadNum) {
        Integer maxCpuNum = Runtime.getRuntime().availableProcessors();
        if (threadNum > maxCpuNum) {
            threadNum = maxCpuNum;
        }

        return Executors.newFixedThreadPool(threadNum);
    }

    /**
     * 获取单个的线程池
     *
     * @return
     */
    public static ExecutorService getSingleThreadPool() {
        return Executors.newSingleThreadExecutor();
    }

    /**
     * 获取可缓存线程的线程池
     *
     * @return
     */
    public static ExecutorService getCachedThreadPool() {
        return Executors.newCachedThreadPool();
    }


    /**
     * 获取带定时任务的线程池
     *
     * @return
     */
    public static ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor() {
        ThreadFactory threadFactory = new ThreadPoolExecutorFactoryBean();
        Integer maxCpuNum = Runtime.getRuntime().availableProcessors();
        // jdk 提示这样可以命名线程
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(maxCpuNum, threadFactory);

        return scheduledThreadPoolExecutor;
    }

    /**
     * jdk1.7 加的，一个分治类 ForkJoinPool
     * @return
     */

    public static ForkJoinPool getForkJoinPool() {
        /*
        一般我们不需要直接使用 ForkJoinTask<V>，而是通过继承它的子类 RecursiveAction
        和 RecursiveTask 并实现对应的抽象方法 —— compute ，来定义我们自己的任务。
        其中，RecursiveAction 是不带返回值的 Fork/Join 型任务，所以使用此类任务并不产生结果，
        也就不涉及到结果的合并；而 RecursiveTask 是带返回值的 Fork/Join 型任务，
        使用此类任务需要我们进行结果的合并。启动，通过 ForkJoinTask<V> 的 fork 方法，我们可以产生子任务并执行；
        通过 join 方法，我们可以获得子任务的结果。
        ForkJoinPool 相比于 ThreadPoolExecutor，还有一个非常重要的特点（优点）在于，
        ForkJoinPool具有 Work-Stealing （工作窃取）的能力。所谓 Work-Stealing，在 ForkJoinPool 中的实现为：
        线程池中每个线程都有一个互不影响的任务队列（双端队列），线程每次都从自己的任务队列的队头中取出一个任务来运行；
        如果某个线程对应的队列已空并且处于空闲状态，而其他线程的队列中还有任务需要处理但是该线程处于工作状态，
        那么空闲的线程可以从其他线程的队列的队尾取一个任务来帮忙运行 —— 感觉就像是空闲的线程去偷人家的任务来运行一样，
        所以叫 “工作窃取”。

        Work-Stealing 的适用场景是不同的任务的耗时相差比较大，即某些任务需要运行较长时间，而某些任务会很快的运行完成，
        这种情况下用 Work-Stealing 很合适；但是如果任务的耗时很平均，则此时 Work-Stealing 并不适合，
        因为窃取任务时不同线程需要抢占锁，这可能会造成额外的时间消耗，而且每个线程维护双端队列也会造成更大的内存消耗。
        所以 ForkJoinPool 并不是 ThreadPoolExecutor 的替代品，而是作为对 ThreadPoolExecutor 的补充。

        总结：
        ForkJoinPool 和 ThreadPoolExecutor 都是 ExecutorService（线程池），但ForkJoinPool 的独特点在于：

        ThreadPoolExecutor 只能执行 Runnable 和 Callable 任务，而 ForkJoinPool 不仅可以执行 Runnable 和 Callable 任务，
        还可以执行 Fork/Join 型任务 —— ForkJoinTask —— 从而满足并行地实现分治算法的需要；
        ThreadPoolExecutor 中任务的执行顺序是按照其在共享队列中的顺序来执行的，所以后面的任务需要等待前面任务执行完毕后才能执行，
        而 ForkJoinPool 每个线程有自己的任务队列，并在此基础上实现了 Work-Stealing 的功能，
        使得在某些情况下 ForkJoinPool 能更大程度的提高并发效率。
         */

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool;
    }

}
