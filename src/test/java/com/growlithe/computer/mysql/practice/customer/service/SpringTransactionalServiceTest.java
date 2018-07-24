package com.growlithe.computer.mysql.practice.customer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author : Growlithe
 * @Date : 2018/7/24 15:33
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTransactionalServiceTest {

    @Autowired
    private SpringTransactionalService springTransactionalService;

    @Test
    public void commonTransactionalTest() {
        springTransactionalService.commonTransactional();
    }


}