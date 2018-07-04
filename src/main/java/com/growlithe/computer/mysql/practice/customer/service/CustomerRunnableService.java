package com.growlithe.computer.mysql.practice.customer.service;

import com.growlithe.computer.config.utils.AutowiredUtils;
import com.growlithe.computer.config.utils.MySqlDataSourceConfigUtils;
import com.growlithe.computer.mysql.practice.customer.dao.domain.CustomerDO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author : Growlithe
 * @Date : 2018/7/3 0:06
 * @Description
 */
@Service(value = "customerRunnableService")
public class CustomerRunnableService implements Runnable {

    private String threadName;

    private List<CustomerDO> customerDOList;

    private Boolean resultFlag = false;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public List<CustomerDO> getCustomerDOList() {
        return customerDOList;
    }

    public void setCustomerDOList(List<CustomerDO> customerDOList) {
        this.customerDOList = customerDOList;
    }

    /**
     * Async 异步标签
     *
     * @return
     */
    @Override
    @Async
    public synchronized void run() {
        /*
        另外一个例子,不会报异常，所以事务都会无效
         */
        DefaultTransactionDefinition defaultTransactionDefinition = this.getDefaultTransactionDefinition();
        PlatformTransactionManager platformTransactionManager = this.getPlatformTransactionManager();
        TransactionStatus transactionStatus = this.getTransactionStatus(platformTransactionManager, defaultTransactionDefinition);

        try {
            AutowiredUtils.customerMapper.saveBatch(customerDOList);
            if ("two".equals(threadName)) {
                try {
                    Integer bug = 2 / 0;
                } catch (ArithmeticException e) {
                    System.out.println("故意出个bug，返回false");

                }
            }
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
        }

    }

    private DefaultTransactionDefinition getDefaultTransactionDefinition() {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return defaultTransactionDefinition;
    }

    private PlatformTransactionManager getPlatformTransactionManager() {
        PlatformTransactionManager platformTransactionManager = MySqlDataSourceConfigUtils.applicationContext.getBean(PlatformTransactionManager.class);
        return platformTransactionManager;
    }

    private TransactionStatus getTransactionStatus(PlatformTransactionManager platformTransactionManager, DefaultTransactionDefinition defaultTransactionDefinition) {
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(defaultTransactionDefinition);
        return transactionStatus;
    }
}
