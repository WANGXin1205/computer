package com.growlithe.computer.mysql.computer.video.service;

import com.growlithe.computer.common.CandyResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Author : Growlithe
 * @Date : 2018/5/21 23:30
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(value = true)
@Transactional(transactionManager = "mysqlTransactionManager")
public class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    @Test
    public void getAllCapacity() {
        CandyResult<BigDecimal> candyResult = videoService.getAllCapacity();
        Assert.assertEquals(true,candyResult.isSuccess());
    }
}