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

    /**
     * 默认进制单位
     */
    private static final BigDecimal STANDARD_UNIT = new BigDecimal("1024");
    /**
     * 默认保留小数位数
     */
    private static final Integer DIGIT = 4;

    /**
     * KB 转换为 MB
     * @param kbCapacity
     * @return
     */
    public static BigDecimal convertKBToMB(BigDecimal kbCapacity) {
       return StorageUnitUtils.upNextUnit(kbCapacity);
    }

    /**
     * MB 转换为 GB
     * @param mbCapacity
     * @return
     */
    public static BigDecimal convertMBToGB(BigDecimal mbCapacity) {
        return StorageUnitUtils.upNextUnit(mbCapacity);
    }

    /**
     * KB 转换为 MB
     * @param kbCapacity
     * @return
     */
    public static BigDecimal convertKBToGB(BigDecimal kbCapacity) {
        return StorageUnitUtils.upNextUnit(StorageUnitUtils.upNextUnit(kbCapacity));
    }

    /**
     * TB 转换为 GB
     * @param tbCapacity
     * @return
     */
    public static BigDecimal convertTBToGB(BigDecimal tbCapacity) {
        return StorageUnitUtils.downNextUnit(StorageUnitUtils.upNextUnit(tbCapacity));
    }

    /**
     * 转换为上一级单位
     * @param capacity
     * @return
     */
    private static BigDecimal upNextUnit(BigDecimal capacity) throws ArithmeticException{
        return capacity.divide(STANDARD_UNIT).setScale(DIGIT,RoundingMode.FLOOR);
    }

    /**
     * 转换为下一级单位
     * @param capacity
     * @return
     */
    private static BigDecimal downNextUnit(BigDecimal capacity){
        return capacity.multiply(STANDARD_UNIT);
    }
}
