package com.growlithe.computer.common.math.domain.dao;

/**
 * @Author : Growlithe
 * @Date : 2018/9/7 上午9:51
 * @Description
 */
public class ChineseRemainderTheoremBean {
    /**
     * 除数
     */
    private Integer divisor;
    /**
     * 余数
     */
    private Integer remainder;

    public ChineseRemainderTheoremBean(Integer divisor, Integer remainder) {
        this.divisor = divisor;
        this.remainder = remainder;
    }

    public Integer getDivisor() {
        return divisor;
    }

    public void setDivisor(Integer divisor) {
        this.divisor = divisor;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }

    @Override
    public String toString() {
        return "ChineseRemainderTheoremBean{" +
                "divisor=" + divisor +
                ", remainder=" + remainder +
                '}';
    }
}
