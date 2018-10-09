package com.growlithe.computer.common.math;


import com.growlithe.computer.common.math.domain.dao.ChineseRemainderTheoremBean;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author : Growlithe
 * @Date : 2018/10/7 下午11:37
 * @Description
 */
public class ChineseRemainderTheoremUtilsTest {

    @Test
    public void solveTest() {
        var chineseRemainderTheoremBean0 = new ChineseRemainderTheoremBean(3, 1);
        var chineseRemainderTheoremBean1 = new ChineseRemainderTheoremBean(5, 3);
        var chineseRemainderTheoremBean2 = new ChineseRemainderTheoremBean(7, 5);
        var chineseRemainderTheoremBean3 = new ChineseRemainderTheoremBean(9, 7);
        List<ChineseRemainderTheoremBean> list = Lists.newArrayList(chineseRemainderTheoremBean0,
                chineseRemainderTheoremBean1,chineseRemainderTheoremBean2,chineseRemainderTheoremBean3);
        ChineseRemainderTheoremUtils.solve(list);
    }

}