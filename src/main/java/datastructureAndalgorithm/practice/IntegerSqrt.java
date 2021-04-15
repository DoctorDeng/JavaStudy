package datastructureAndalgorithm.practice;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 求整数的算数平方根, 结果精确 6 位小数.
 * 习题参考：<a href="https://leetcode-cn.com/problems/sqrtx/description/">Leetcode-69</a>
 * @author <a href="http://doctordeng.github.io">DoctorDeng</a>
 * @date 2021/4/14 21:53
 * @since 1.0.0
 */
public class IntegerSqrt {

    /**
     * 求正整数的算术平方根, 利用二分查找实现.
     * @param value 正整数
     * @return 给定值的算术平方根, 保留 6 为小数
     */
    public static double sqrt(int value) {
        positiveIntegerCheck(value);
        return sqrt(value, 6);
    }

    public static double sqrt(int value, int digit) {
        positiveIntegerCheck(value);

        double start = 0;
        double end = value;
        double precision = precision(digit);
        while (end>=start) {
            double mid = start + ((end - start)/2.00);
            double midSquare = mid * mid;
            double difference = midSquare - value;
            if (difference == 0) {
                return round(mid, digit);
            }
            // 差值在指定精度下则算 mid 值未 value 的算术平方根
            else if (difference <= precision
                    && difference >= -precision) {
                return round(mid, digit);
            } else if (difference > 0) {
                end = mid;
            } else if (difference < 0){
                start = mid;
            }
        }
        throw new RuntimeException("sqrt error");
    }

    public static double precision(int digit) {
        digitCheck(digit);
        double precision = 1.0;
        for (int i = 1; i <= digit; i++) {
            precision = precision/10.0;
        }

        return precision;
    }

    /**
     * 对给定值保留指定位精度（会做四色五入操作）.
     * @param value 给定值
     * @param digit 保留小数位数, 必须大于等于 0
     * @return 保留指定小数位数的值
     */
    public static double round(double value, int digit) {
        if (digit < 0) throw new IllegalArgumentException();
        return new BigDecimal(value).setScale(digit, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 对给定值保留指定位精度（不会做四色五入操作）.
     * @param value 给定值
     * @param digit 保留小数位数, 比如大于等于 0，小于等于 16
     * @return 保留指定小数位数的值
     */
    @Deprecated
    private static double range(double value, int digit) {
        digitCheck(digit);
        if (value == 0) {
            return value;
        }
        if (digit == 0) {
            return (long) value;
        }
        if (digit == 16) {
            return value;
        }
        long precision = 1;
        for (int i = 1; i <= digit; i++) {
            precision = precision * 10;
        }
        return  ((long)(value*precision))/(precision*1.0);
    }
    @Deprecated
    private static void digitCheck(int digit) {
        if (digit < 0 || digit > 16) {
            throw new IllegalArgumentException("double digit range is 0 ~ 16, digit=" + digit);
        }
    }

    private static void positiveIntegerCheck(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be greater than or equal to 0, value=" + value);
        }
    }
}
