package com.growlithe.computer.mysql.practice.customer.service;

import com.google.common.collect.Lists;
import com.growlithe.computer.common.CandyResult;
import com.growlithe.computer.mysql.practice.customer.dao.domain.CustomerDO;
import com.growlithe.computer.mysql.practice.customer.dao.domain.EmpDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/7/24 14:42
 * @Description
 */
@Service("springTransactionalService")
public class SpringTransactionalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringTransactionalService.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmpService empService;

    /**
     * 配置初始值
     *
     * @return
     */
    public static List<CustomerDO> getCustomerDOList() {

        CustomerDO customerDO = new CustomerDO();

        customerDO.setFirstName("Candy");
        customerDO.setLastName("miaomiao");

        List<CustomerDO> customerDOList = Lists.newArrayList(customerDO);

        return customerDOList;
    }

    /**
     * 事务传播的探讨
     *
     * @return
     */
    @Transactional(value = "mysqlTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public CandyResult commonTransactional() {
        CandyResult candyResult = new CandyResult();
        List<CustomerDO> customerDOList = SpringTransactionalService.getCustomerDOList();

        /*
        PROPAGATION_REQUIRED commonTransactional Propagation.REQUIRED
                             saveBatchException Propagation.REQUIRED
                             如果存在一个事务，则支持当前事务。如果没有事务则开启一个新的事务。
                             调用commonTransactional时，环境中没有事务，所以开启一个新的事务.
                             当在commonTransactional中调用saveBatchException时，环境中已经有了一个事务，
                             所以saveBatchException就加入当前事务,这也是经常用的

        PROPAGATION_SUPPORTS commonTransactional Propagation.REQUIRED
                             saveBatchException Propagation.SUPPORTS
                             单纯的调用saveBatchException时，saveBatchException方法是非事务的执行的。
                             当调用commonTransactional时,saveBatchException则加入了commonTransactional的事务中,事务地执行。

        PROPAGATION_MANDATORY commonTransactional Propagation.REQUIRED
                              saveBatchException Propagation.MANDATORY
                              当单独调用saveBatchException时，因为当前没有一个活动的事务，
                              则会抛出异常throw new IllegalTransactionStateException(“Transaction propagation ‘mandatory’
                              but no existing transaction found”);当调用commonTransactional时，
                              saveBatchException则加入到commonTransactional的事务中，事务地执行。
        PROPAGATION_REQUIRES_NEW commonTransactional Propagation.REQUIRED
                                 saveBatchException Propagation.REQUIRES_NEW
                                 总是开启一个新的事务。如果一个事务已经存在，则将这个存在的事务挂起。
                                 直到其他事务执行完毕，再处理该事物。

        PROPAGATION_NOT_SUPPORTED 总是非事务地执行，并挂起任何存在的事务。
                                  使用PROPAGATION_NOT_SUPPORTED,也需要使用JtaTransactionManager作为事务管理器。

        PROPAGATION_NEVER 总是非事务地执行，如果存在一个活动事务，则抛出异常。

        PROPAGATION_NESTED 如果一个活动的事务存在，则运行在一个嵌套的事务中.
                           如果没有活动事务, 则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行。
                           这是一个嵌套事务,使用JDBC 3.0驱动时,仅仅支持DataSourceTransactionManager作为事务管理器。
                           需要JDBC 驱动的java.sql.Savepoint类。有一些JTA的事务管理器实现可能也提供了同样的功能。
                           使用PROPAGATION_NESTED，还需要把PlatformTransactionManager的nestedTransactionAllowed属性设为true;
                           而nestedTransactionAllowed属性值默认为false。

         */
        customerService.saveBatchException(customerDOList);

        candyResult.setSuccess(true);
        return candyResult;
    }


}
