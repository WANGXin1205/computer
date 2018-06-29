package com.growlithe.computer.common.math;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @Author : Growlithe
 * @Date : 2018/6/26 23:26
 * @Description
 */
public class SortTest {

    @Test
    public void bubbleSortTest() {
        var list = new Integer[]{7,4,9,2,4,1,3};
        Sort.bubbleSort(list);
        Stream.of(list).forEach(System.out::println);
        Assert.assertArrayEquals(new Integer[]{1,2,3,4,4,7,9},list);
    }

    @Test
    public void selectionSortTest(){
        var list = new Integer[]{7,4,9,2,4,1,3};
        Sort.selectionSort(list);
        Stream.of(list).forEach(System.out::println);
        Assert.assertArrayEquals(new Integer[]{1,2,3,4,4,7,9},list);
    }

    @Test
    public void insertionSortTest(){
        var list = new Integer[]{7,4,9,2,4,1,3};
        Sort.insertionSort(list);
        Stream.of(list).forEach(System.out::println);
    }
}