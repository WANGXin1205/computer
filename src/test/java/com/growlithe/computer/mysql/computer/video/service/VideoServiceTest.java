package com.growlithe.computer.mysql.computer.video.service;

import com.google.common.collect.Lists;
import com.growlithe.computer.common.CandyResult;
import com.growlithe.computer.mysql.computer.video.service.emuns.VideoClassEnum;
import org.apache.spark.sql.catalyst.expressions.aggregate.Collect;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
    public void listAllVideoTest(){
        var candyResult = videoService.listAllVideo();
        Assert.assertTrue(candyResult.isSuccess());
    }

    @Test
    public void getAllCapacity() {
        CandyResult<BigDecimal> candyResult = videoService.getAllCapacity();
        Assert.assertTrue(candyResult.isSuccess());
    }

    @Test
    public void getCapacityTest(){
        CandyResult<BigDecimal> candyResult = videoService.getCapacity(VideoClassEnum.CARTOON);
        Assert.assertTrue(candyResult.isSuccess());
    }

}