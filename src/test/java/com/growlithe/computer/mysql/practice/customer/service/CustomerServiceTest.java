package com.growlithe.computer.mysql.practice.customer.service;

import com.growlithe.computer.mysql.practice.customer.dao.domain.CustomerDO;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author : Growlithe
 * @Date : 2018/7/3 21:21
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void saveBatchTest() {
        CustomerDO customerDO = new CustomerDO();
        customerDO.setFirstName("candy");
        customerDO.setLastName("candy");

        List<CustomerDO> customerDOList = Lists.newArrayList(customerDO);
        var candyResult = customerService.saveBatch(customerDOList);
        Assert.assertTrue(candyResult.isSuccess());
    }

    @Test
    public void saveBatchByFutureTest() {
        CustomerDO customerDO = new CustomerDO();
        customerDO.setFirstName("candy");
        customerDO.setLastName("candy");
        CustomerDO customerDO1 = new CustomerDO();
        customerDO1.setFirstName("candy1");
        customerDO1.setLastName("candy1");
        CustomerDO customerDO2 = new CustomerDO();
        customerDO2.setFirstName("candy2");
        customerDO2.setLastName("candy2");
        CustomerDO customerDO3 = new CustomerDO();
        customerDO3.setFirstName("candy3");
        customerDO3.setLastName("candy3");

        List<CustomerDO> customerDOList = Lists.newArrayList(customerDO, customerDO1, customerDO2, customerDO3);
        var candyResult = customerService.saveBatchByFuture(customerDOList);

        Assert.assertTrue(candyResult.isSuccess());
    }

    @Test
    public void saveBatchByRunnableTest() {
        CustomerDO customerDO = new CustomerDO();
        customerDO.setFirstName("candy");
        customerDO.setLastName("candy");
        CustomerDO customerDO1 = new CustomerDO();
        customerDO1.setFirstName("candy1");
        customerDO1.setLastName("candy1");
        CustomerDO customerDO2 = new CustomerDO();
        customerDO2.setFirstName("candy2");
        customerDO2.setLastName("candy2");
        CustomerDO customerDO3 = new CustomerDO();
        customerDO3.setFirstName("candy3");
        customerDO3.setLastName("candy3");

        List<CustomerDO> customerDOList = Lists.newArrayList(customerDO, customerDO1, customerDO2, customerDO3);
        var candyResult = customerService.saveBatchByRunnable(customerDOList);

        Assert.assertTrue(candyResult.isSuccess());
    }
}