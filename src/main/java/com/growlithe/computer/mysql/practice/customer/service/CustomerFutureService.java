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
@Service(value = "customerFutureService")
public class CustomerFutureService implements Callable<Boolean> {

    /**
     * 为什么要用 Callable呢？ 因为thread 和 runnable 没有返回状态 所以子线程抛出错误没有办法控制 所以用callable,但是发现主线程似乎不支持啊
     *
     * @return
     */
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
    public synchronized Boolean call() {
        /*
         既然子线程可以回滚，那么thread 和 runnable 也应该可以回滚，事务一定要提前拿到，如果在catch 里面再搞事务，不能回滚
         但是如果不用Callable，子线程不会返回任何结果，数据也没有保存至数据库，但是主线程结果会为true。
         */
        DefaultTransactionDefinition defaultTransactionDefinition = this.getDefaultTransactionDefinition();
        PlatformTransactionManager platformTransactionManager = this.getPlatformTransactionManager();
        TransactionStatus transactionStatus = this.getTransactionStatus(platformTransactionManager, defaultTransactionDefinition);

        try {
            // spring 对多线程的支持不是太好，所以要从配置文件中获取bean这个容器。的确是依赖注入的问题
            AutowiredUtils.customerMapper.saveBatch(customerDOList);
            if ("two".equals(threadName)) {
                try {
                    Integer bug = 2 / 0;
                } catch (ArithmeticException e) {
                    System.out.println("故意出个bug，返回false");
                    return resultFlag;
                }
            }
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
            return resultFlag;
        }

        return resultFlag = true;
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
