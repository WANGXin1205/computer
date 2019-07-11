package com.growlithe.computer.mysql.practice.customer.utils;

import com.growlithe.computer.mysql.practice.customer.thread.*;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spark_project.jetty.util.StringUtil;
import org.spark_project.jetty.util.thread.ExecutorThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tukaani.xz.CorruptedInputException;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * @Author : Growlithe
 * @Date : 2018/6/29 0:12
 * @Description
 */
public class ThreadUtilsTest {

    /**
     * 如果用单元测试，不进行依赖注入，会造成打印失效，还是用main方法直观
     */
    private static final String threadName = "first thread";
    private static final String threadName1 = "second thread";

    @Before
    private static void myThreadStart() {
        MyThread myThread = ThreadUtils.getThreadByMyThread(threadName);
        MyThread myThread1 = ThreadUtils.getThreadByMyThread(threadName1);
        myThread.start();
        myThread1.start();
    }

    @Before
    private static void myRunnableStart() {
        MyRunnable myRunnable = ThreadUtils.getThreadByMyRunnable(threadName);
        MyRunnable myRunnable1 = ThreadUtils.getThreadByMyRunnable(threadName1);
        // 这里使用了new Thread的两种构造方法，但是 java 不建议用new Thread 方法创建线程，而要用工厂类或者Executors创建线程
        new Thread(myRunnable).start();
        new Thread(myRunnable1, myRunnable1.getThreadName()).start();
    }

    @Before
    private static void futureTaskStart() {
        FutureTask<Integer> futureTask = ThreadUtils.getThreadByMyCallable(threadName);
        FutureTask<Integer> futureTask1 = ThreadUtils.getThreadByMyCallable(threadName1);
        new Thread(futureTask).start();
        new Thread(futureTask1).start();
    }

    @Before
    private static void maxCpuNumThreadPoolStart() {
        MyThread myThread = ThreadUtils.getThreadByMyThread(threadName);
        MyThread myThread1 = ThreadUtils.getThreadByMyThread(threadName1);
        ExecutorService executorService = ThreadUtils.getMaxCpuNumThreadPool();
        executorService.execute(myThread);
        executorService.execute(myThread1);
    }

    @Before
    private static void singleThreadPoolStart() {
        MyRunnable myRunnable = ThreadUtils.getThreadByMyRunnable(threadName);
        MyRunnable myRunnable1 = ThreadUtils.getThreadByMyRunnable(threadName1);
        ExecutorService executorService = ThreadUtils.getSingleThreadPool();
        executorService.execute(myRunnable);
        executorService.execute(myRunnable1);
    }

    @Before
    private static void cachedThreadPoolStart() {
        MyRunnable myRunnable = ThreadUtils.getThreadByMyRunnable(threadName);
        MyRunnable myRunnable1 = ThreadUtils.getThreadByMyRunnable(threadName1);
        ExecutorService executorService = ThreadUtils.getCachedThreadPool();
        executorService.execute(myRunnable);
        executorService.execute(myRunnable1);
    }

    @Before
    private static void cachedThreadPoolSubmit() {
        MyRunnable myRunnable = ThreadUtils.getThreadByMyRunnable(threadName);
        MyRunnable myRunnable1 = ThreadUtils.getThreadByMyRunnable(threadName1);
        ExecutorService executorService = ThreadUtils.getCachedThreadPool();
        /*
         submit 提交一个task 给线程池，该task 可以是带返回结果的 Callable<V> 任务，
         也可以是一开始就指定结果的 Runnable 任务，或者不带结果的 Runnable 任务
         submit 方法是非阻塞的，每次调用 submit 方法提交任务到线程池之后，
         会立即返回与任务相关联的 Future<V>，然后当前线程继续向后执行
          */
        executorService.submit(myRunnable);
        executorService.submit(myRunnable1);
    }

    @Before
    private static void invokeAll(List<MyCallable> myCallableList) {
        ExecutorService executorService = ThreadUtils.getCachedThreadPool();
        try {
            executorService.invokeAll(myCallableList);
        } catch (InterruptedException e) {
            System.out.println("this is a InterruptedException");
        }

    }

    @Before
    private static void invokeAny(List<MyCallable> myCallableList) {
        ExecutorService executorService = ThreadUtils.getCachedThreadPool();
        try {
            executorService.invokeAny(myCallableList);
        } catch (InterruptedException e) {
            System.out.println("this is a InterruptedException");
        } catch (ExecutionException e) {
            System.out.println("this is a ExecutionException");
        }

    }

    /**
     * invoke 方法
     *
     * @param allOrAny
     */
    private static void invoke(String allOrAny) {
        if (StringUtils.isNullOrEmpty(allOrAny)) {
            return;
        }

        MyCallable myCallable = ThreadUtils.getMyCallable(threadName);
        MyCallable myCallable1 = ThreadUtils.getMyCallable(threadName1);
        List<MyCallable> myCallableList = Lists.newArrayList(myCallable, myCallable1);
         /*
         invokeAll invokeAny 方法是阻塞的
         只有当提交的多个任务都执行完毕之后，invokeAll 方法才会返回，执行结果会以List<Future<V>> 返回,
         该 List<Future<V>> 中的每个 Future<V> 是和提交任务时的 Collection<Callable<V>> 中的任务 Callable<V> 一 一对应的。
         invokeAny 与 invokeAll 方法的不同之处在于，当所提交的一组任务中的任何一个任务完成之后，
         invokeAny 方法便会返回（返回的结果便是那个已经完成的任务的返回值），而其他任务会被取消（中断）
         PS: 这里all 和 any 应该定义成final
          */
        if ("all".equals(allOrAny)) {
            ThreadUtilsTest.invokeAll(myCallableList);
        }
        if ("any".equals(allOrAny)) {
            ThreadUtilsTest.invokeAny(myCallableList);
        }
    }

    /**
     * 获取线程状态
     *
     * @param executorService
     * @return
     */
    private static Boolean getThreadShutdownFlag(ExecutorService executorService) {
        Boolean shutdownFlag = Boolean.FALSE;
        try {
            shutdownFlag = executorService.awaitTermination(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("this is a InterruptedException");
        }
        return shutdownFlag;
    }

    /**
     * 线程的关闭
     */
    private static void shutdownStart() {
        MyThread myThread = ThreadUtils.getThreadByMyThread(threadName);
        MyThread myThread1 = ThreadUtils.getThreadByMyThread(threadName1);
        ExecutorService executorService = ThreadUtils.getMaxCpuNumThreadPool();
        executorService.execute(myThread);
        executorService.execute(myThread1);

        /*
            isTerminated() 方法，如果线程池已经调用 shutdown 并且线程池中所有的任务已经执行完毕，
            或者线程池调用了 shutdownNow，则返回 true，否则返回 false。
        */
        Boolean terminatedFlag = executorService.isTerminated();
        System.out.println("thread shutdown status is " + terminatedFlag);
        Boolean shutdownFlag = ThreadUtilsTest.getThreadShutdownFlag(executorService);

        System.out.println("thread shutdown status is " + shutdownFlag);
        // 判断是否调用shutdown 或者 shutdownNow
        System.out.println("thread shutdown status is " + executorService.isShutdown());
//            executorService.shutdown();
        executorService.shutdownNow();
        // 线程池关闭后再来加入新的线程，会报错
//            executorService.execute(myThread1);
        shutdownFlag = ThreadUtilsTest.getThreadShutdownFlag(executorService);
        // 效率低
        System.out.println("thread shutdown status is " + shutdownFlag);
        System.out.println("thread shutdown status is " + terminatedFlag);
        System.out.println("thread shutdown status is " + executorService.isShutdown());
    }

    /**
     * 取消线程任务 即使抛出异常，还是会执行完
     */
    private static void cancelTask() {
        MyThread myThread = ThreadUtils.getThreadByMyThread(threadName);
        MyThread myThread1 = ThreadUtils.getThreadByMyThread(threadName1);
        // 线程重启就是重新创建
        ExecutorService executorService = ThreadUtils.getMaxCpuNumThreadPool();
        Future future1 = executorService.submit(myThread);
        Future future2 = executorService.submit(myThread1);

        System.out.println("future1 isCancelled result is" + future1.isCancelled());
        System.out.println("future1 isDone result is" + future1.isDone());
        System.out.println("future2 isCancelled result is" + future2.isCancelled());
        System.out.println("future2 isDone result is" + future2.isDone());

         /*
            如果任务运行之前调用了该方法，那么任务就不会被运行；
            如果任务已经完成或者已经被取消，那么该方法方法不起作用；
            如果任务正在运行，并且 cancel 传入参数为 true，那么便会去终止与 Future 关联的任务。
            cancel(false) 与 cancel(true）的区别在于，cancel(false) 只 取消已经提交但还没有被运行的任务（即任务就不会被安排运行）；
            而 cancel(true) 会取消所有已经提交的任务，包括 正在等待的 和 正在运行的 任务。
          */
        future1.cancel(true);
        future2.cancel(false);

        try {
            /*
            get 方法 用来返回和 Future 关联的任务的结果。
            带参数的 get 方法指定一个超时时间，在超时时间内该方法会阻塞当前线程，直到获得结果 。
             */
            double time = (double) future1.get(10, TimeUnit.MILLISECONDS);
            System.out.println("任务运行时间: " + time);
        } catch (TimeoutException e) {
            // 如果在给定的超时时间内没有获得结果，那么便抛出 TimeoutException 异常；
        } catch (CancellationException e) {
            //执行的任务被取消（此时抛出 CancellationException 异常）；
        } catch (ExecutionException e) {
            // 执行任务时出错，即执行过程中出现异常（此时抛出 ExecutionException 异常）；
        } catch (InterruptedException e) {
            // 当前线程被中断（此时抛出 InterruptedException 异常 —— 注意，当前线程是指调用 get 方法的线程，而不是运行任务的线程）。
        }

        /*
        isCancelled 方法：该方法是非阻塞的。在任务结束之前，如果任务被取消了，该方法返回 true，否则返回 false；如果任务已经完成，该方法则一直返回 false。
        isDone 方法：该方法同样是非阻塞的。如果任务已经结束（正常结束，或者被取消，或者执行出错），返回 true，否则返回 false。
         */
        System.out.println("future1 isCancelled result is" + future1.isCancelled());
        System.out.println("future1 isDone result is" + future1.isDone());
        System.out.println("future2 isCancelled result is" + future2.isCancelled());
        System.out.println("future2 isDone result is" + future2.isDone());
    }

    /**
     * 取消任务
     *
     * @param future
     * @param delayTime
     */
    private static void cancelTask(final Future<?> future, final Integer delayTime) {
        Runnable cancellation = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(delayTime);
                    future.cancel(true); // 取消与 future 关联的正在运行的任务
                } catch (InterruptedException ex) {
                    ex.printStackTrace(System.err);
                }
            }
        };

        new Thread(cancellation).start();
    }

    /**
     * 取消任务的例子
     */
    public static void cancelTaskDemo() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        SimpleTask task = new SimpleTask(3_000); // task 需要运行 3 秒
        Future<Double> future = threadPool.submit(task);
        threadPool.shutdown(); // 发送关闭线程池的指令

        cancelTask(future, 2_000);

        double time = Double.NaN;
        try {
            time = future.get();
        } catch (CancellationException e) {
            System.err.println("任务被取消");
        } catch (InterruptedException e) {
            System.err.println("当前线程被中断");
        } catch (ExecutionException e) {
            System.err.println("任务执行出错");
        }

        System.out.format("任务运行时间: %.3f s\n", time);
    }

    /**
     * 定时任务线程
     */
    public static void scheduledThreadPoolExecutorDemo(){
        Long delayTime = 10000L;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = ThreadUtils.getScheduledThreadPoolExecutor();
        // 一个Runnable ，一个Callable<V> 假设 Callable 调用该方法后的时间点为 0，
        scheduledThreadPoolExecutor.schedule(ThreadUtilsTest::myRunnableStart,delayTime,TimeUnit.MILLISECONDS);
        System.out.println("schedule finish");

        /*
            那么第一次执行任务的时间点为 initialDelay，第二次为 initialDelay + period，
            第三次为 initialDelay + period + period，以此类推。
        */
        scheduledThreadPoolExecutor.scheduleAtFixedRate(ThreadUtilsTest::myRunnableStart,delayTime,delayTime,TimeUnit.MILLISECONDS);
        System.out.println("scheduleAtFixedRate finish");
        /*
            假设调用该方法后的时间点为 0，每次任务需要耗时 T(i)（i 为第几次执行任务），
            那么第一次执行任务的时间点为 initialDelay，第一次完成任务的时间点为 initialDelay + T(1)，
            则第二次执行任务的时间点为 initialDelay + T(1) + delay；第二次完成任务的时间点为 initialDelay + (T(1) + delay) + T(2)，
            所以第三次执行任务的时间点为 initialDelay + T(1) + delay + T(2) + delay，以此类推。
         */

        try {
            Thread.sleep(delayTime);
        }catch (Exception e){

        }
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(ThreadUtilsTest::myRunnableStart,delayTime,delayTime,TimeUnit.MILLISECONDS);
        System.out.println("scheduleWithFixedDelay finish");
    }

    /**
     * 计算pi的例子
     */
    public static void piInvokeDemo(){
        Integer maxCpuNum = Runtime.getRuntime().availableProcessors();
        ForkJoinPool forkJoinPool = new ForkJoinPool(maxCpuNum);

        // 计算 10 亿项，分割任务的临界值为 1 千万
        PiEstimateTask task = new PiEstimateTask(0, 1_000_000_000, 10_000_000);

        // 阻塞，直到任务执行完毕返回结果
        double pi = forkJoinPool.invoke(task);

        System.out.println("π 的值：" + pi);

        // 向线程池发送关闭的指令
        forkJoinPool.shutdown();
    }

    /**
     * 计算pi的例子
     */
    public static void piSubmitDemo(){
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        PiEstimateTask task = new PiEstimateTask(0, 1_000_000_000, 10_000_000);
        // 不阻塞
        Future<Double> future = forkJoinPool.submit(task);

        double pi =Double.NaN;
        try {
            pi = future.get();
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println("π 的值：" + pi);
        System.out.println("future 指向的对象是 task 吗：" + (future == task));

        // 向线程池发送关闭的指令
        forkJoinPool.shutdown();
    }

    private static final ThreadLocal<SimpleDateFormat> SDF
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("thread test, please enter int num range 1 to 15:\n");
        Integer runNo = 0;
        try {
            runNo = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("please input a int num");
            ThreadUtilsTest.main(args);
        }
        // 其实不应该出现魔数
        if (runNo < 1 || runNo > 15) {
            System.out.println("wrong num");
            ThreadUtilsTest.main(args);
        }
        if (1 == runNo) {
            ThreadUtilsTest.myThreadStart();
        }
        if (2 == runNo) {
            ThreadUtilsTest.myRunnableStart();
        }
        if (3 == runNo) {
            ThreadUtilsTest.futureTaskStart();
        }
        if (4 == runNo) {
            ThreadUtilsTest.maxCpuNumThreadPoolStart();
        }
        if (5 == runNo) {
            ThreadUtilsTest.singleThreadPoolStart();
        }
        if (6 == runNo) {
            ThreadUtilsTest.cachedThreadPoolStart();
        }
        if (7 == runNo) {
            ThreadUtilsTest.cachedThreadPoolSubmit();
        }
        if (8 == runNo) {
            ThreadUtilsTest.invoke("all");
        }
        if (9 == runNo) {
            ThreadUtilsTest.invoke("any");
        }
        if (10 == runNo) {
            ThreadUtilsTest.shutdownStart();
        }
        if (11 == runNo) {
            ThreadUtilsTest.cancelTask();
        }
        if (12 == runNo) {
            ThreadUtilsTest.cancelTaskDemo();
        }
        if (13 == runNo) {
            ThreadUtilsTest.scheduledThreadPoolExecutorDemo();
        }
        if (14 == runNo){
            ThreadUtilsTest.piInvokeDemo();
        }
        if (15 == runNo) {
            ThreadUtilsTest.piSubmitDemo();
        }
    }

}

