package com.growlithe.computer.common.math;

import org.junit.Assert;
import org.junit.Test;

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

}