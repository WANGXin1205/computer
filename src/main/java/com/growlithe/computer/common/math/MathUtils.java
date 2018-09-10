package com.growlithe.computer.common.math;

import com.growlithe.computer.excepetion.TransactionException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

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

    private static final Integer ZERO = 0;
    private static final Integer LIST_SIZE_IS_TWO = 2;

    /**
     * 一般判断素数的方法 为了消耗时间也是醉了
     *
     * @param num
     * @return
     */
    public static Boolean isOriginPrimeNumber(Integer num) {

        for (long i = 2; i < num; i++) {
            if (num % i == ZERO) {
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
            if (num % x == ZERO) {
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
     * 获取数组中最小的数
     *
     * @param array
     * @return
     */
    public static Integer getMin(Integer[] array) {
        MathUtils.checkIntegerArray(array);
        Integer min = array[ZERO];

        for (Integer i = ZERO; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }

        return min;
    }

    /**
     * 求两个数的最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static Integer getGreatestCommonDivisor(Integer a, Integer b) {
        if (ZERO.equals(a) || ZERO.equals(b)) {
            throw new TransactionException("a or b must not is zero,but now a is : " + a + ",b is : " + b);
        }
        Integer min = Math.min(a, b);
        Integer max = Math.max(a, b);
        while (max % min != ZERO) {
            Integer mod = max % min;
            max = min;
            min = mod;
        }

        return min;
    }

    /**
     * 求列表中数字的最大公约数
     *
     * @param list
     * @return
     */
    public static Integer getGreatestCommonDivisor(List<Integer> list) {
        Integer greatestCommonDivisor = MathUtils.checkListInGetGreatestCommonDivisor(list);
        if (greatestCommonDivisor == null) {
            // 如果最大公约数为空，则进行最大公约数的计算
            greatestCommonDivisor = list.get(0);
            for (Integer x : list) {
                greatestCommonDivisor = MathUtils.getGreatestCommonDivisor(greatestCommonDivisor, x);
            }
        }

        return greatestCommonDivisor;
    }

    /**
     * 参数检查 求列表中数字的最大公约数
     *
     * @param list
     */
    private static Integer checkListInGetGreatestCommonDivisor(List<Integer> list) {
        Integer greatestCommonDivisor = MathUtils.checkListInCommonStep(list);
        if (list.size() == LIST_SIZE_IS_TWO) {
            greatestCommonDivisor = MathUtils.getGreatestCommonDivisor(list.get(0), list.get(1));
        }

        return greatestCommonDivisor;
    }

    /**
     * 参数检查 求最大公约数 和 最小公倍数 的共同步骤
     *
     * @param list
     * @return
     */
    private static Integer checkListInCommonStep(List<Integer> list) {
        Integer integer = null;
        if (CollectionUtils.isEmpty(list)) {
            throw new TransactionException("list must be not null");
        }
        boolean zeroFlag = list.stream().anyMatch(ZERO::equals);
        if (zeroFlag) {
            throw new TransactionException("list cannot contain 0");
        }
        if (list.size() == 1) {
            integer = list.get(ZERO);
        }

        return integer;
    }

    /**
     * 求两个数的最小公倍数
     *
     * @param a
     * @param b
     * @return
     */
    public static Integer getLeastCommonMultiple(Integer a, Integer b) {
        Integer greatestCommonDivisor = MathUtils.getGreatestCommonDivisor(a, b);
        return a * b / greatestCommonDivisor;
    }

    /**
     * 求列表中数字的最大公约数
     *
     * @param list
     * @return
     */
    public static Integer getLeastCommonMultiple(List<Integer> list) {
        Integer leastCommonMultiple = MathUtils.checkListInGetLeastCommonMultiple(list);
        if (leastCommonMultiple == null) {
            // 如果最大公约数为空，则进行最大公约数的计算
            leastCommonMultiple = list.get(0);
            for (Integer x : list) {
                leastCommonMultiple = MathUtils.getLeastCommonMultiple(leastCommonMultiple, x);
            }
        }
        return leastCommonMultiple;
    }

    /**
     * 参数检查 求列表中数字的最大公约数
     *
     * @param list
     */
    private static Integer checkListInGetLeastCommonMultiple(List<Integer> list) {
        Integer leastCommonMultiple = MathUtils.checkListInCommonStep(list);
        if (list.size() == LIST_SIZE_IS_TWO) {
            leastCommonMultiple = MathUtils.getLeastCommonMultiple(list.get(0), list.get(1));
        }

        return leastCommonMultiple;
    }

}
