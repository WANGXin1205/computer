package com.growlithe.computer.common.math;

import com.growlithe.computer.excepetion.TransactionException;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/6/26 23:20
 * @Description
 */
public class Sort {

    /**
     * zero
     */
    private static final Integer DEFAULT_ARRAY_START = 0;
    /**
     * one
     */
    private static final Integer ONE = 1;

    public static Boolean checkSigle(Integer[] array) {
        if (array.length == ONE) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 冒泡排序
     *
     * @param array
     */
    public static void bubbleSort(Integer[] array) {
        MathUtils.checkIntegerArray(array);
        if (Sort.checkSigle(array)) {
            return;
        }
        for (int i = DEFAULT_ARRAY_START; i < array.length - ONE; i++) {
            for (int j = DEFAULT_ARRAY_START; j < array.length - ONE; j++) {
                if (array[j] > array[j + ONE]) {
                    Integer temp = array[j];
                    array[j] = array[j + ONE];
                    array[j + ONE] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param array
     */
    public static void selectionSort(Integer[] array) {
        MathUtils.checkIntegerArray(array);
        if (Sort.checkSigle(array)) {
            return;
        }

        for (Integer i = DEFAULT_ARRAY_START; i < array.length; i++) {

        }
    }

}
