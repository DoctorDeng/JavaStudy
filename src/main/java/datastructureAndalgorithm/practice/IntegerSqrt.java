package datastructureAndalgorithm.practice;

/**
 * 求整数的算数平方根, 结果精确 6 位小数.
 * 习题参考：<a href="https://leetcode-cn.com/problems/sqrtx/description/">Leetcode-69</a>
 * @author <a href="http://doctordeng.github.io">DoctorDeng</a>
 * @date 2021/4/14 21:53
 * @since 1.0.0
 */
public class IntegerSqrt {

    public static double sqrt(int value) {
        int abs = Math.abs(value);

        double start = 0;
        double end = value;
        while (end>=start) {
            double mid = start + ((end - start)/2.00);
            double mid2 = mid * mid;
            double difference = mid2 - value;
            if (difference == 0) {
                return mid;
            } else if (difference <= 0.000001 && difference >= -0.000001) {
                return mid;
            } else if (difference > 0) {
                end = mid;
            } else if (difference < 0){
                start = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
       System.out.println(sqrt(8));
       System.out.println(2.82842712474619*2.82842712474619);
       System.out.println(2.8284270763397217*2.8284270763397217);
    }
}
