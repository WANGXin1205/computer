package com.growlithe.computer.mysql.practice.customer.dao.mapper;

import com.growlithe.computer.mysql.practice.customer.dao.domain.CustomerDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:17
 * @Description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(value = true)
@Transactional(transactionManager = "mysqlTransactionManager")
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void listAllCustomerDO() {
        List<CustomerDO> customerList = customerMapper.listAllCustomerDO();
        Assert.assertNotNull(customerList);
    }

}