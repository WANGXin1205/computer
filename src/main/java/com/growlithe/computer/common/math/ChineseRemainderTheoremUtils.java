package com.growlithe.computer.common.math;

import com.growlithe.computer.common.CandyResult;
import com.growlithe.computer.common.math.domain.dao.ChineseRemainderTheoremBean;
import com.growlithe.computer.excepetion.TransactionException;
import net.razorvine.pickle.Pair;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.spark.sql.catalyst.expressions.In;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author : Growlithe
 * @Date : 2018/9/7 8:10
 * @Description
 */
public class ChineseRemainderTheoremUtils {

    private static Integer DEFAULT_POINT = 10;

    /**
     * 根据孙子定理（中国余数定理）解方程
     *
     * @param list
     */
    public static void solve(List<ChineseRemainderTheoremBean> list) {
        ChineseRemainderTheoremUtils.checkData(list);
        Integer remaindersLeastCommonMultiple = ChineseRemainderTheoremUtils.preprocessData(list);
        ChineseRemainderTheoremUtils.algorithm(list, remaindersLeastCommonMultiple);
    }

    /**
     * 参数检查
     *
     * @param list
     */
    private static void checkData(List<ChineseRemainderTheoremBean> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new TransactionException("列表为空");
        }

        HashMap<Integer, Integer> originDataHashMap = new HashMap<>();
        int i = 1;
        for (ChineseRemainderTheoremBean x : list) {

            // 如果系数不为1，系数默认值1
            if (x.getCoefficient() == null || 1 != x.getCoefficient()) {
                x.setCoefficient(1);
            }
            // 检查除数不为空
            if (x.getDivisor() == null) {
                throw new TransactionException("第" + i + "行中，除数为空");
            }
            // 检查余数不为空
            if (x.getRemainder() == null) {
                throw new TransactionException("第" + i + "行中，余数为空");
            }
            // 检查相同的除数，余数是否一致
            if (!originDataHashMap.containsKey(x.getDivisor())) {
                originDataHashMap.put(x.getDivisor(), x.getRemainder());
            } else {
                boolean haveTwoRemainderFlag = !originDataHashMap.get(x.getDivisor()).equals(x.getRemainder());
                if (haveTwoRemainderFlag) {
                    throw new TransactionException(x.getDivisor() + "的余数不能确定，请检查" + x.getDivisor() + "的余数");
                }
            }

            i++;
        }

    }

    /**
     * 数据预处理
     *
     * @param list
     */
    private static Integer preprocessData(List<ChineseRemainderTheoremBean> list) {

        // 提取余数列表
        var originDataDivisorList = list.stream().map(ChineseRemainderTheoremBean::getDivisor).collect(Collectors.toList());

        // 求余数最小公倍数
        Integer divisorLeastCommonMultiple = MathUtils.getLeastCommonMultiple(originDataDivisorList);

        return divisorLeastCommonMultiple;
    }

    /**
     * 解题 算法
     *
     * @param list
     * @param divisorLeastCommonMultiple 余数最小公倍数
     */
    private static void algorithm(List<ChineseRemainderTheoremBean> list, Integer divisorLeastCommonMultiple) {

        // 给被除数、余数及其对应的除数，扩大 余数最小公倍数/余数 倍  并将所有等式相加
        ChineseRemainderTheoremBean chineseRemainderTheoremBean = new ChineseRemainderTheoremBean(0, 0, 0);
        list.forEach(x -> {
            Integer leastCommonMultiple = divisorLeastCommonMultiple / x.getDivisor();

            Integer coefficient = x.getCoefficient() * leastCommonMultiple;
            Integer divisor = x.getDivisor() * leastCommonMultiple;
            Integer remainder = x.getRemainder() * leastCommonMultiple;

            x.setCoefficient(coefficient);
            x.setDivisor(divisor);
            x.setRemainder(remainder);

            // 将所有等式相加 系数、除数相加，余数不变
            Integer tempCoefficient = chineseRemainderTheoremBean.getCoefficient() + coefficient;
            Integer tempRemainder = chineseRemainderTheoremBean.getRemainder() + remainder;
            chineseRemainderTheoremBean.setCoefficient(tempCoefficient);
            chineseRemainderTheoremBean.setDivisor(divisor);
            chineseRemainderTheoremBean.setRemainder(tempRemainder);
        });

        // 如果系数、除数比余数大，则系数、除数一直减去余数，直到它们比余数小
        Integer tempCoefficientMultiple = chineseRemainderTheoremBean.getCoefficient() / chineseRemainderTheoremBean.getDivisor();
        Integer tempRemainderMultiple = chineseRemainderTheoremBean.getRemainder() / chineseRemainderTheoremBean.getDivisor();
        Integer coefficient = chineseRemainderTheoremBean.getCoefficient() - chineseRemainderTheoremBean.getDivisor() * tempCoefficientMultiple;
        Integer remainder = chineseRemainderTheoremBean.getRemainder() - chineseRemainderTheoremBean.getDivisor() * tempRemainderMultiple;
        chineseRemainderTheoremBean.setCoefficient(coefficient);
        chineseRemainderTheoremBean.setRemainder(remainder);

        // 先得到一个特解
        Integer i = 1;
        BigDecimal bigDecimal = new BigDecimal("0.1");
        while (!MathUtils.isIntegerNumber(bigDecimal)){

            bigDecimal = BigDecimal.valueOf(chineseRemainderTheoremBean.getDivisor() * i + chineseRemainderTheoremBean.getRemainder())
                    .divide(BigDecimal.valueOf(chineseRemainderTheoremBean.getCoefficient()),DEFAULT_POINT,RoundingMode.DOWN);
            i++;
        }

        // 再求出通解

        System.out.println("result is :" + chineseRemainderTheoremBean.getDivisor() + "n +" + bigDecimal.intValue()
                + ", where n equal 0,1,2,3...");

    }


}
