package com.growlithe.computer.mysql.practice.customer.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author : Growlithe
 * @Date : 2018/7/2 21:41
 * @Description
 */
public class DateFormatThread {

    public static void main(String[] args) throws Exception {
        // 创建无大小限制的线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            DateFormatTask task = new DateFormatTask();
            // 将任务提交到线程池
            Future<?> future = threadPool.submit(task);

            futures.add(future);
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (ExecutionException ex) {
                System.err.println("执行时出现异常：" + ex.getMessage());
            }
        }

        threadPool.shutdown();
    }

    static class DateFormatTask implements Callable<Void> {

        @Override
        public Void call() throws Exception {
            String str = DateFormatWrapper.format(
                    DateFormatWrapper.parse("2017-07-17 16:54:54"));
            System.out.printf("Thread(%s) -> %s\n", Thread.currentThread().getName(), str);

            return null;
        }

    }
}
