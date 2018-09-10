package com.growlithe.computer.common.math;

import com.growlithe.computer.common.CandyResult;
import com.growlithe.computer.common.math.domain.dao.ChineseRemainderTheoremBean;
import com.growlithe.computer.excepetion.TransactionException;
import javafx.util.Pair;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/9/7 8:10
 * @Description
 */
public class ChineseRemainderTheoremUtils {

    /**
     * 根据孙子定理（中国余数定理）解方程
     *
     * @param list
     */
    public void solve(List<ChineseRemainderTheoremBean> list) {
        checkData(list);

    }

    /**
     * 参数检查
     *
     * @param list
     */
    private void checkData(List<ChineseRemainderTheoremBean> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new TransactionException("列表为空");
        }
        int i = 1;
        for (ChineseRemainderTheoremBean x : list) {
            if (x.getDivisor() == null) {
                throw new TransactionException("第" + i + "行中，除数为空");
            }
            if (x.getRemainder() == null) {
                throw new TransactionException("第" + i + "行中，余数为空");
            }
            i++;
        }

    }

    /**
     * 数据预处理
     *
     * @param list
     */
    private void preprocessData(List<ChineseRemainderTheoremBean> list) {
        // 将数据转为HashMap
        HashMap<Integer, Integer> originDataHashMap = new HashMap<>();
        list.forEach(x -> {
            if (!originDataHashMap.containsKey(x.getDivisor())) {
                originDataHashMap.put(x.getDivisor(), x.getRemainder());
            } else {
                throw new TransactionException(x.getDivisor() + "的余数不能确定，请检查" + x.getDivisor() + "的余数");
            }
        });

        //求mod最小公倍数


    }

}
