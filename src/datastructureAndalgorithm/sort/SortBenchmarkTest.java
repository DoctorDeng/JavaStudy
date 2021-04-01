package datastructureAndalgorithm.sort;

import datastructureAndalgorithm.utils.SortUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

/**
 * 排序性能测试
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/3/29 20:41
 * @since 1.0.0
 */
@RunWith(Parameterized.class)
public class SortBenchmarkTest {

    private int[] testData;

    @Parameterized.Parameters
    public static Object[][] data() {
        // 执行 100 次
        return new Object[10][0];
    }

    @Before
    public void initData() throws Exception {
        testData = SortUtil.getTestData(100000);
    }

    @Test
    public void bubbleSort() {
        sort(() -> BubbleSort.bestSort(testData));
    }

    @Test
    public void insertionSort() {
        sort(() -> InsertionSort.sort(testData, testData.length));
    }

    @Test
    public void cocktailSort() {
        sort(() -> CocktailSort.sort(testData));
    }

    @Test
    public void quickSort() {
        sort(() -> new QuickSort().sort(testData));
    }

    @Test
    public void arraysSort() {
        sort(() -> Arrays.sort(testData));
    }

    private void sort(Function function) {
        long startTime = System.currentTimeMillis();
        function.run();
        long endTime = System.currentTimeMillis();
        double timeConsuming = endTime - startTime;
        System.out.printf("%s 个数据, 耗时 %.5f 毫秒", testData.length, timeConsuming);
        System.out.println();
    }

    @FunctionalInterface
    interface Function {
        /**
         * do some thing
         */
        void run();
    }
}
