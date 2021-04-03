package datastructureAndalgorithm.sort;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * 排序测试.
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/3/29 20:41
 * @since 1.0.0
 */
@RunWith(Parameterized.class)
public class SortTest {

    private int[] testData;

    @Parameterized.Parameters
    public static Object[][] data() {
        // 执行 10 次
        return new Object[1][0];
    }

    @Before
    public void initData() throws Exception {
        testData = SortUtils.getTestData(100000);
    }

    @Test
    public void bubbleSort() {
        sort(new BubbleSort());
    }

    @Test
    public void insertionSort() {
        sort(new InsertionSort());
    }

    @Test
    public void cocktailSort() {
        sort(new CocktailSort());
    }

    @Test
    public void quickSort() {
        sort(new QuickSort());
    }

    @Test
    public void arraysSort() {
        sort(Arrays::sort, "Arrays 排序");
    }

    @Test
    public void mergeSort() {
        sort(new MergeSort());
    }

    private void sort(Sort sort) {
        sort(sort::sort, sort.getName());
    }

    private void sort(Consumer<int[]> consumer, String name) {
        long startTime = System.currentTimeMillis();
        consumer.accept(testData);
        long endTime = System.currentTimeMillis();
        BasicSort.checkSort(testData);
        double timeConsuming = endTime - startTime;
        System.out.printf("排序名称: %s, 共排序 %s 个数据, 耗时 %.2f 毫秒", name, testData.length, timeConsuming);
        System.out.println();
    }
}
