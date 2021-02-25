package com.haso.common.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * <p>
 * 数量计算工具类
 * </p>
 *
 * @Author fengbo.ge
 * @since 2020-05-20
 */
@Component
public class MathUtil {

    /**
     * 两数相减
     */
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 两数相加
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 两数相除
     */
    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        return a.divide(b,10, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 两数相乘
     */
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b).setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 两数相减
     */
    public static double subtract(double a, double b) {
        return new BigDecimal(a).subtract(new BigDecimal(b)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 两数相加
     */
    public static double add(double a, double b) {
        return new BigDecimal(a).add(new BigDecimal(b)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 两数相除
     */
    public static double divide(double a, double b) {
        return (new BigDecimal(a).divide(new BigDecimal(b),10, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 两数相除
     */
    public static double divide(double a, double b, int c) {
        return (new BigDecimal(a).divide(new BigDecimal(b), 10, BigDecimal.ROUND_HALF_UP)).setScale(c, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 两数相乘
     */
    public static double multiply(double a, double b) {
        return new BigDecimal(a).multiply(new BigDecimal(b)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 小数处理
     */
    public static BigDecimal scale(BigDecimal a,  int length) {
        return a.setScale(length, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 绝对值
     */
    public static double abs(double a) {
        return new BigDecimal(a).abs().doubleValue();
    }



}
