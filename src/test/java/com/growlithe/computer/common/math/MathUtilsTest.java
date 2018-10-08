package com.growlithe.computer.common.math;

import org.apache.spark.sql.sources.In;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author : Growlithe
 * @Date : 2018/7/1 23:05
 * @Description
 */
public class MathUtilsTest {

    @Test
    public void isPrimeNumber() {
        var num = 37;
        Boolean primeFlag = MathUtils.isPrimeNumber(num);
        Assert.assertTrue(primeFlag);
    }

    @Test
    public void isIntegerNumberTest() {
        BigDecimal bigDecimal = new BigDecimal("29.00");
        Boolean flag = MathUtils.isIntegerNumber(bigDecimal);
        Assert.assertTrue(flag);
        bigDecimal = new BigDecimal("29.10");
        flag = MathUtils.isIntegerNumber(bigDecimal);
        Assert.assertTrue(!flag);
    }


    @Test
    public void getGreatestCommonDivisorTest(){
        Integer a = 3;
        Integer b = 5;
        Integer greatestCommonDivisor = MathUtils.getGreatestCommonDivisor(a,b);
        Assert.assertEquals(1,greatestCommonDivisor.intValue());
        a = 2;
        b = 4;
        greatestCommonDivisor = MathUtils.getGreatestCommonDivisor(a,b);
        Assert.assertEquals(2,greatestCommonDivisor.intValue());
        a = 12;
        b = 15;
        greatestCommonDivisor = MathUtils.getGreatestCommonDivisor(a,b);
        Assert.assertEquals(3,greatestCommonDivisor.intValue());
    }

    @Test
    public void getGreatestCommonDivisorTest1(){
        var list = Lists.newArrayList(1,2,3,4,5);
        var greatestCommonDivisor = MathUtils.getGreatestCommonDivisor(list);
        Assert.assertEquals(1,greatestCommonDivisor.intValue());
        list = Lists.newArrayList(2,4,6,8,10);
        greatestCommonDivisor = MathUtils.getGreatestCommonDivisor(list);
        Assert.assertEquals(2,greatestCommonDivisor.intValue());
        list = Lists.newArrayList(2);
        greatestCommonDivisor = MathUtils.getGreatestCommonDivisor(list);
        Assert.assertEquals(2,greatestCommonDivisor.intValue());
        list = Lists.newArrayList(2,4);
        greatestCommonDivisor = MathUtils.getGreatestCommonDivisor(list);
        Assert.assertEquals(2,greatestCommonDivisor.intValue());
    }

    @Test
    public void getLeastCommonMultipleTest(){
        Integer a = 3;
        Integer b = 5;
        Integer leastCommonMultiple = MathUtils.getLeastCommonMultiple(a,b);
        Assert.assertEquals(15,leastCommonMultiple.intValue());
        a = 2;
        b = 4;
        leastCommonMultiple = MathUtils.getLeastCommonMultiple(a,b);
        Assert.assertEquals(4,leastCommonMultiple.intValue());
    }

    @Test
    public void getLeastCommonMultipleTest1(){
        var list = Lists.newArrayList(1,2,3,5);
        Integer leastCommonMultiple = MathUtils.getLeastCommonMultiple(list);
        Assert.assertEquals(30,leastCommonMultiple.intValue());
        list = Lists.newArrayList(2,2,4,4,8);
        leastCommonMultiple = MathUtils.getLeastCommonMultiple(list);
        Assert.assertEquals(8,leastCommonMultiple.intValue());
    }
}