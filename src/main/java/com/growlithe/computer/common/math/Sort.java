package com.growlithe.computer.common.math;

import com.growlithe.computer.excepetion.TransactionException;
import org.apache.spark.sql.sources.In;
import org.springframework.util.CollectionUtils;

import java.util.*;

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

    /**
     * 冒泡排序 时间复杂度O(n^2)
     *
     * @param array
     */
    public static void bubbleSort(Integer[] array) {
        MathUtils.checkIntegerArray(array);
        if (Sort.checkSingle(array)) {
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
     * 选择排序 时间复杂度O(n^2)
     *
     * @param array
     */
    public static void selectionSort(Integer[] array) {
        MathUtils.checkIntegerArray(array);
        if (Sort.checkSingle(array)) {
            return;
        }

        for (Integer i = DEFAULT_ARRAY_START; i < array.length; i++) {
            Integer minIndex = i;
            for (Integer j = i + ONE; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            Integer temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    /**
     * 插入排序 时间复杂度O(n^2)
     *
     * @param array
     */
    public static void insertionSort(Integer[] array) {
        MathUtils.checkIntegerArray(array);
        if (Sort.checkSingle(array)) {
            return;
        }

        for (Integer i = ONE; i < array.length; i++) {
            Integer j = i - ONE;
            Integer currentValue = array[i];
            while (j >= DEFAULT_ARRAY_START && currentValue < array[j]) {
                array[j + ONE] = array[j];
                j--;
            }
            array[j + ONE] = currentValue;
        }
    }

    /**
     * 快速排序 时间复杂度O(n log2(N))
     *
     * @param array
     */
    public static void quickSort(Integer[] array) {
        MathUtils.checkIntegerArray(array);
        if (Sort.checkSingle(array)) {
            return;
        }

    }

    /**
     * 计数排序 时间复杂度
     * @param array
     */
    public static void countSort(Integer[] array){
        MathUtils.checkIntegerArray(array);
        if (Sort.checkSingle(array)) {
            return;
        }

        TreeMap<Integer,Integer> countTreeMap = new TreeMap<>();
        Arrays.stream(array).forEach(x->{
            if (!countTreeMap.containsKey(x)){
                countTreeMap.put(x,ONE);
            }else {
                countTreeMap.put(x,countTreeMap.get(x) + ONE);
            }
        });

        Integer i = 0;
        for (Integer x: countTreeMap.keySet()){
             while (!DEFAULT_ARRAY_START.equals(countTreeMap.get(x))){
                 array[i] = x;
                 countTreeMap.put(x,countTreeMap.get(x)-ONE);
                 i++;
             }
        }

    }

    /**
     * 单数检查
     *
     * @param array
     * @return
     */
    private static Boolean checkSingle(Integer[] array) {
        if (array.length == ONE) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
