package com.growlithe.computer.mysql.practice.customer.thread;

import java.util.concurrent.RecursiveTask;

/**
 * @Author : Growlithe
 * @Date : 2018/7/1 22:23
 * @Description
 */
public class PiEstimateTask extends RecursiveTask<Double> {

    private final long begin;
    private final long end;
    /**
     * 分割任务的临界值
     */
    private final long threshold;

    public PiEstimateTask(long begin, long end, long threshold) {
        this.begin = begin;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected Double compute() {
        // 临界值之下，不再分割，直接计算
        if (end - begin <= threshold) {

            // 符号，取 1 或者 -1
            int sign = 1;
            double result = 0.0;
            for (long i = begin; i < end; i++) {
                result += sign / (i * 2.0 + 1);
                sign = -sign;
            }

            return result * 4;
        }

        // 分割任务
        long middle = (begin + end) / 2;
        PiEstimateTask leftTask = new PiEstimateTask(begin, middle, threshold);
        PiEstimateTask rightTask = new PiEstimateTask(middle, end, threshold);

        // 异步执行 leftTask rightTask
        leftTask.fork();
        rightTask.fork();

        /*
          阻塞，直到 leftTask 执行完毕返回结果 可以看出，Join方法实现是通过wait（小提示：Object 提供的方法）。
          当main线程调用t.join时候，main线程会获得线程对象t的锁（wait 意味着拿到该对象的锁),调用该对象的wait(等待时间)，
          直到该对象唤醒main线程 ，比如退出后。这就意味着main 线程调用t.join时，必须能够拿到线程t对象的锁
         */
        double leftResult = leftTask.join();
        // 阻塞，直到 rightTask 执行完毕返回结果
        double rightResult = rightTask.join();

        // 合并结果
        return leftResult + rightResult;
    }
}
