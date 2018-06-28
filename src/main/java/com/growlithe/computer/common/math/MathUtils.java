package com.growlithe.computer.common.math;

import com.growlithe.computer.excepetion.TransactionException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author : Growlithe
 * @Date : 2018/6/26 23:49
 * @Description
 */
public class MathUtils {

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
