package com.growlithe.computer.common.math;

import com.growlithe.computer.excepetion.TransactionException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author : Growlithe
 * @Date : 2018/6/26 23:49
 * @Description
 */
public class MathUtils {

    /**
     * 一般判断素数的方法 为了消耗时间也是醉了
     *
     * @param num
     * @return
     */
    public static Boolean isOriginPrimeNumber(Integer num) {

        for (long i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断素数的方法
     *
     * @param num
     * @return
     */
    public static Boolean isPrimeNumber(Integer num) {

        Double infEDouble = Math.sqrt(Double.valueOf(num));
        Integer infEInteger = new BigDecimal(infEDouble).setScale(0, RoundingMode.UP).intValue();

        for (Integer x = 2; x < infEInteger; x++) {
            if (num % x == 0) {
                return false;
            }
        }

        return true;
    }


    /**
     * 检查Integer数组是否为空
     *
     * @param array
     */
    public static void checkIntegerArray(Integer[] array) {
        if (array == null) {
            throw new TransactionException("list must not null");
        }
    }

    /**
     * todo 我忘了我写啥了
     *
     * @param array
     * @return
     */
    public static Integer getMin(Integer[] array) {
        MathUtils.checkIntegerArray(array);
        Integer min = array[0];

        for (Integer i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }

        return min;
    }
}
