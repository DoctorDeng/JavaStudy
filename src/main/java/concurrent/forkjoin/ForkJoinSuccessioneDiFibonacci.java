package concurrent.forkjoin;

import datastructureAndalgorithm.practice.SuccessioneDiFibonacci;
import org.springframework.util.Assert;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 使用 Fork/Join 实现斐波拉契数列求和, 普通求解可查看 {@link SuccessioneDiFibonacci}.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @see SuccessioneDiFibonacci
 * @date 2021/8/14 15:08
 */
public class ForkJoinSuccessioneDiFibonacci {

    // 该示例仅用于练习 Fork/Join 的使用, 当计算过大的数据时会长时间占满 CPU.
    private static Long caculate(long n) {
        assertPositiveInteger(n);
        if (n < 2) {
            return n;
        }
        // 1. 创建分治任务线程池.
        ForkJoinPool pool = new ForkJoinPool(4);
        // 2. 创建分支任务.
        CaculateTask taskN = new CaculateTask(n);
        // 3. 启动任务计算结果.
        long result = pool.invoke(taskN);
        pool.shutdown();
        return result;
    }

    private static void assertPositiveInteger(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("N cannot be less than 0, n is " + n);
        }
    }

    static class CaculateTask extends RecursiveTask<Long> {

        private final long n;

        public CaculateTask(long n) {
            assertPositiveInteger(n);
            this.n = n;
        }

        @Override
        protected Long compute() {
            if (n < 2) {
                return n;
            }
            // 创建子任务
            CaculateTask n_1 = new CaculateTask(n - 1);
            n_1.fork();
            // 等待子任务结果，并合并结果
            CaculateTask n_2 = new CaculateTask(n - 2);
            return n_2.compute() + n_1.join();
        }
    }

    public static void main(String[] args) {
        Assert.isTrue(caculate(10) == SuccessioneDiFibonacci.iterationCalculationN(10));
    }
}
