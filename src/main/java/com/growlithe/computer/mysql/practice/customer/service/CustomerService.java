package com.growlithe.computer.mysql.practice.customer.service;

import com.growlithe.computer.common.CandyResult;
import com.growlithe.computer.excepetion.TransactionException;
import com.growlithe.computer.mysql.practice.customer.dao.domain.CustomerDO;
import com.growlithe.computer.mysql.practice.customer.dao.mapper.CustomerMapper;
import com.growlithe.computer.mysql.practice.customer.utils.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;

/**
 * @Author : Growlithe
 * @Date : 2018/7/3 21:19
 * @Description
 */
@Component
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 一个简单的插入
     *
     * @param customerDOList
     * @return
     */
    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public CandyResult saveBatch(List<CustomerDO> customerDOList) {
        CandyResult candyResult = new CandyResult();
        customerMapper.saveBatch(customerDOList);

        candyResult.setSuccess(true);
        return candyResult;
    }

    /**
     * 一个简单的插入
     *
     * @param customerDOList
     * @return
     */
    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public CandyResult saveBatchException(List<CustomerDO> customerDOList) {
        CandyResult candyResult = new CandyResult();
        customerMapper.saveBatch(customerDOList);
        try {
            Integer bug = 2 / 0;
        } catch (ArithmeticException e) {
            throw new TransactionException("故意出bug，查看回滚没");
        }

        candyResult.setSuccess(true);
        return candyResult;
    }

    /**
     * 多线程的回滚 by future。spring的回滚注释无效，需要在子线程搞
     *
     * @param customerDOList
     * @return
     */
    public CandyResult saveBatchByFuture(List<CustomerDO> customerDOList) {
        CandyResult candyResult = new CandyResult();

        List<CustomerDO> customerDOS = new ArrayList<>();
        List<CustomerDO> customerDOS1 = new ArrayList<>();
        if (customerDOList.size() > 2) {
            for (int i = 0; i < customerDOList.size(); i++) {
                if (i < 2) {
                    customerDOS.add(customerDOList.get(i));
                } else {
                    customerDOS1.add(customerDOList.get(i));
                }
            }
        }

        CustomerFutureService customerFutureService = new CustomerFutureService();
        customerFutureService.setThreadName("one");
        customerFutureService.setCustomerDOList(customerDOS);
        CustomerFutureService customerFutureService1 = new CustomerFutureService();
        customerFutureService1.setThreadName("two");
        customerFutureService1.setCustomerDOList(customerDOS1);

        // FutureTask 继承了 RunnableFuture，RunnableFuture 继承了Runnable和Future
        FutureTask<Boolean> futureTask = new FutureTask<>(customerFutureService);
        FutureTask<Boolean> futureTask1 = new FutureTask<>(customerFutureService1);

        ExecutorService executorService = ThreadUtils.getCachedThreadPool();
        executorService.submit(futureTask);
        executorService.submit(futureTask1);

        // 如果不进行判断，会不知道线程的执行结果
        try {
            Boolean futureTaskResult = futureTask.get();
            Boolean futureTaskResult1 = futureTask1.get();
            Boolean falseFlag = !(futureTaskResult && futureTaskResult1);
            if (falseFlag) {
                throw new TransactionException("有线程发生错误，开始回滚");
            }
        } catch (Exception e) {
            throw new TransactionException(e);
        }

        candyResult.setSuccess(true);
        return candyResult;
    }

    /**
     * 多线程的回滚 by runnable
     *
     * @param customerDOList
     * @return
     */
    public CandyResult saveBatchByRunnable(List<CustomerDO> customerDOList) {
        CandyResult candyResult = new CandyResult();

        List<CustomerDO> customerDOS = new ArrayList<>();
        List<CustomerDO> customerDOS1 = new ArrayList<>();
        if (customerDOList.size() > 2) {
            for (int i = 0; i < customerDOList.size(); i++) {
                if (i < 1) {
                    customerDOS.add(customerDOList.get(i));
                } else {
                    customerDOS1.add(customerDOList.get(i));
                }
            }
        }

        CustomerRunnableService customerRunnableService = new CustomerRunnableService();
        customerRunnableService.setThreadName("one");
        customerRunnableService.setCustomerDOList(customerDOS);
        CustomerRunnableService customerRunnableService1 = new CustomerRunnableService();
        customerRunnableService1.setThreadName("two");
        customerRunnableService1.setCustomerDOList(customerDOS1);

        ThreadFactory threadFactory = new ThreadPoolExecutorFactoryBean();


        Thread thread = threadFactory.newThread(customerRunnableService);
        thread.start();
        Thread thread1 = threadFactory.newThread(customerRunnableService1);
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (Exception e) {

        }


        candyResult.setSuccess(true);
        return candyResult;
    }

}
