package com.growlithe.computer.mysql.practice.customer.service;

import com.growlithe.computer.mysql.practice.customer.dao.domain.CustomerDO;
import com.growlithe.computer.mysql.practice.customer.dao.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author : Growlithe
 * @Date : 2018/7/3 0:06
 * @Description
 */
@Component
public class CustomerFutureService implements Callable<Boolean> {

    /**
     * 为什么要用 Callable呢？ 因为thread 和 runnable 没有返回状态 所以子线程抛出错误没有办法控制 所以用callable
     *
     * @return
     */
    @Autowired
    private CustomerMapper customerMapper;

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

    @Override
    @Async
    public synchronized Boolean call() {
        customerMapper.saveBatch(customerDOList);

//        if ("two".equals(threadName)){
//            try {
//                Integer bug = 2/0;
//            }catch (ArithmeticException e){
//                System.out.println("故意出个bug，返回false");
//                return resultFlag;
//            }
//        }

        return resultFlag = true;
    }
}
