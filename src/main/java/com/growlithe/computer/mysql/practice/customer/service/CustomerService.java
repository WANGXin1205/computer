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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            Integer bug = 2 / 0;
        } catch (ArithmeticException e) {
            throw new TransactionException("故意出bug，查看回滚没");
        }

        candyResult.setSuccess(true);
        return candyResult;
    }

    /**
     * thread 的回滚
     *
     * @param customerDOList
     * @return
     */
    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
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

//        try {
//            // 问题是这里为什么会报空指针呢，估计是依赖注入问题
//            Boolean futureTaskResult = futureTask.get();
//            Boolean futureTaskResult1 = futureTask1.get();
//            Boolean falseFlag = !(futureTaskResult && futureTaskResult1);
//            if (falseFlag) {
//                throw new TransactionException("有线程发生错误，开始回滚");
//            }
//        } catch (Exception e) {
//            throw new TransactionException(e);
//        }

        candyResult.setSuccess(true);
        return candyResult;
    }


}
