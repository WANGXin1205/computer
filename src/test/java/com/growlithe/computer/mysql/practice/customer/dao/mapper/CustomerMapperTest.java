package com.growlithe.computer.mysql.practice.customer.dao.mapper;

import com.growlithe.computer.mysql.practice.customer.dao.domain.CustomerDO;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import scala.reflect.internal.util.ScalaClassLoader;
import scala.tools.ant.Scalac;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author : Growlithe
 * @Date : 2018/5/19 11:17
 * @Description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
// 事务回滚
//@Rollback(value = true)
//@Transactional(transactionManager = "mysqlTransactionManager")
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void listAllCustomerDO() {
        List<CustomerDO> customerList = customerMapper.listAllCustomerDO();
        Assert.assertNotNull(customerList);
    }

    @Test
    public void saveBatch() {
        CustomerDO customerDO = new CustomerDO();
        customerDO.setFirstName("candy");
        customerDO.setLastName("candy");
        List<CustomerDO> customerList = new ArrayList<>();
        customerList.add(customerDO);

        Integer count = customerMapper.saveBatch(customerList);
        Assert.assertNotNull(count);
    }

    @Test
    public void deleteAllTest() {
        Integer count = customerMapper.deleteAll();
        Assert.assertNotNull(count);
    }

}