package com.growlithe.computer.common.math;

import org.assertj.core.util.Lists;
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
    public void bubbleSort() {
        var list = new Integer[]{7,4,9,2,4,1,3};
        Sort.bubbleSort(list);
        Stream.of(list).forEach(System.out::println);

    }
}