package datastructureAndalgorithm.practice;

/**
 * 求整数的算数平方根, 结果精确 6 位小数.
 * 习题参考：<a href="https://leetcode-cn.com/problems/sqrtx/description/">Leetcode-69</a>
 * @author <a href="http://doctordeng.github.io">DoctorDeng</a>
 * @date 2021/4/14 21:53
 * @since 1.0.0
 */
public class IntegerSqrt {

    public static double sqrt(int value, int digit) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be greater than or equal to 0, value=" + value);
        }
        double start = 0;
        double end = value;
        double precision = precision(digit);
        while (end>=start) {
            double mid = start + ((end - start)/2.00);
            double midSquare = mid * mid;
            double difference = midSquare - value;
            if (difference == 0) {
                return range(mid, digit);
            } else if (difference <= precision && difference >= -precision) {
                return range(mid, digit);
            } else if (difference > 0) {
                end = mid;
            } else if (difference < 0){
                start = mid;
            } else {
                return range(mid, digit);
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

    private static void digitCheck(int digit) {
        if (digit < 0 || digit > 16) {
            throw new IllegalArgumentException("double digit range is 0 ~ 16, digit=" + digit);
        }
    }

    public static double range(double value, int digit) {
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
        return  ((long)(value*precision))/ (precision*1.0);
    }

    public static void main(String[] args) {
        System.out.println(sqrt(5, 6));
        System.out.println(Math.sqrt(5));
    }
}
