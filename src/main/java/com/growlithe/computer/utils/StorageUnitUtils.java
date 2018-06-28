package com.growlithe.computer.utils;

import com.google.gson.Gson;
import com.growlithe.computer.excepetion.TransactionException;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author : Growlithe
 * @Date : 2018/5/21 23:03
 * @Description
 */
public class StorageUnitUtils {

    private static final BigDecimal STANDARD_UNIT = new BigDecimal("1024");

    private static final Integer DIGIT = 4;

    public static BigDecimal convertKBToMB(BigDecimal kbCapacity) {
       return StorageUnitUtils.upNextUnit(kbCapacity);
    }

    public static BigDecimal convertMBToGB(BigDecimal mbCapacity) {
        return StorageUnitUtils.upNextUnit(mbCapacity);
    }

    public static BigDecimal convertKBToGB(BigDecimal kbCapacity) {
        return StorageUnitUtils.upNextUnit(StorageUnitUtils.upNextUnit(kbCapacity));
    }


    public static BigDecimal convertTBToGB(BigDecimal tbCapacity) {
        return StorageUnitUtils.downNextUnit(StorageUnitUtils.upNextUnit(tbCapacity));
    }

    /**
     *
     * @param capacity
     * @return
     */
    private static BigDecimal upNextUnit(BigDecimal capacity) throws ArithmeticException{
        return capacity.divide(STANDARD_UNIT).setScale(DIGIT,RoundingMode.FLOOR);
    }

    /**
     *
     * @param capacity
     * @return
     */
    private static BigDecimal downNextUnit(BigDecimal capacity){
        return capacity.multiply(STANDARD_UNIT);
    }
}
