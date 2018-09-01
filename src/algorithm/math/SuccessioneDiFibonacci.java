package algorithm.math;

/**
 * 斐波那契数列：
 *   由于斐波纳挈数列是以兔子的繁殖引入的，因此也叫“兔子数列”。
 *   它指的是这样一个数列：0,1,1,2,3,5,8,13......从这组数可以很明显看出这样一个规律：从第三个数开始，后边一个数一定是在其之前两个数的和。
 *   在数学上，斐波纳挈数列可以以这样的公式表示：F(0) = 0 F(1) = 1 F(n) = F(n-1) + F(n-2),(n>=2)
 *   具体见：<a href="https://zh.wikipedia.org/wiki/斐波那契数列">维基百科-斐波那契数列</a>
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @since 2018/9/1 21:22
 */
public class SuccessioneDiFibonacci {
    /*
     * 普通递归实现：有堆栈溢出风险，效率极其低下
     * 时间复杂度：O(2^N)
     * Recursion: 递归
     */
    public static long simpleRecursionCalculationN(long n) {
        checkArgument(n);
        if (n < 2) {
            return n;
        }
        // 由于 n-1 和 n-2 。。。的值都由递归一步步算出，计算量巨大导致效率低下
        return (simpleRecursionCalculationN(n-1) + simpleRecursionCalculationN(n-2));
    }

    /*
     * 类似于尾递归实现：有堆栈溢出风险，效率比普通递归高
     * 注意：Java 并没有为尾递归做优化，所以依然有堆栈溢出风险
     * 时间复杂度为O(N)
     */
    public static long optimizationRecursionCalculationN(long n, int v1, int v2) {
        checkArgument(n);
        if (n == 0) {
            return v1;
        }

        return optimizationRecursionCalculationN(n - 1, v2, v1 + v2);
    }
    private static void checkArgument(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("The value must be greater than 0");
        }
    }

    /**
     * 迭代实现，无内存溢出风险，效率高
     * 时间复杂度为O(N)，空间复杂度为O(1)
     */
    public static long iterationCalculationN(long n) {
        checkArgument(n);
        if (n < 2) {
            return n;
        }

        int vN = 0;
        int vN_1 = 0;
        int vN_2 = 1;
        for (int i = 2; i <= n; i++) {
            vN = vN_1 + vN_2;
            vN_1 = vN_2;
            vN_2 = vN;
        }

        return vN;
    }

    public static void main(String[] args) {
        //0,1,1,2,3,5,8,13
        //System.out.println(simpleRecursionCalculationN(10));
        //System.out.println(optimizationRecursionCalculationN(1000, 0, 1));
        System.out.println(iterationCalculationN(10000));
    }
}
