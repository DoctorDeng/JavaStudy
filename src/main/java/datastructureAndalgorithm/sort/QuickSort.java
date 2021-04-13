package datastructureAndalgorithm.sort;

import org.apache.commons.lang3.RandomUtils;

import java.util.Stack;

/**
 * 快速排序.
 *
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/3/31 21:02
 * @since 1.0.0
 */
public class QuickSort extends BasicSort {

    @Override
    public void sort(int[] array) {
        checkArguments(array);
        //quickSortByRecursion(array, 0, array.length -1);
        quickSortByLoop(array, 0, array.length -1);
    }

    @Override
    public String getName() {
        return "快速排序";
    }

    /**
     * 对指定数组指定下标范围进行排序.
     * <p>算法为基于递归实现的快速排序算法, 未做任何优化, 有堆栈溢出风险.
     * @param array 待排序数组
     * @param startIndex  起始下标
     * @param endIndex    结尾下标
     */
    protected static void quickSortByRecursion(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int pivotIndex = lowPartition(array, startIndex, endIndex);
        quickSortByRecursion(array, startIndex, pivotIndex -1);
        quickSortByRecursion(array, pivotIndex + 1, endIndex);
    }

    /**
     * 对指定数组指定下标范围进行排序.
     * <p>算法为基于循环实现的快速排序算法, 未做任何优化.
     * @param array 待排序数组
     * @param startIndex  起始下标
     * @param endIndex    结尾下标
     */
    protected static void quickSortByLoop(int[] array, int startIndex, int endIndex) {
        Stack<Integer> stack = new Stack<>();

        stack.push(startIndex);
        stack.push(endIndex);

        while (!stack.isEmpty()) {
            int _endIdx = stack.pop();
            int _startIdx =  stack.pop();

            int pivotIndex = lowPartition(array, _startIdx, _endIdx);

            if (pivotIndex > _startIdx) {
                stack.push(_startIdx);
                stack.push(pivotIndex - 1);
            }
            if (pivotIndex < _endIdx) {
                stack.push(pivotIndex + 1);
                stack.push(_endIdx);
            }
        }

    }

    /**
     * 对序列进行分区，每次取序列头部元素做基准元素.
     * @param array 待排序序列
     * @param start 子序列起始下标
     * @param end 待排序元素截止下标
     * @return 分区基准元素下标.
     */
    protected static int lowPartition(int[] array, int start, int end) {
        return partition(array, start, end, start);
    }

    /**
     * 对序列进行分区，每次取随机选取一个元素做基准元素.
     * @param array 待排序序列
     * @param start 待排序元素起始下标
     * @param end 子序列截止下标
     * @return 分区基准元素下标.
     */
    protected static int randomPartition(int[] array, int start, int end) {
        return partition(array, start, end, (start + RandomUtils.nextInt(0, end - start)));
    }

    /**
     * 对序列进行分区, 将小于基准元素的元素放在序列左侧, 比大于、等于基准元素的元素放置序列右侧.
     * @param array 待排序序列
     * @param start 子序列起始下标
     * @param end 子序列截止下标
     * @param pivotIndex 基准元素下标
     * @return 分区基准元素下标.
     */
    protected static int partition(int[] array, int start, int end, int pivotIndex) {
        int i = start;
        int pivot = array[pivotIndex];

        for (int j = start; j <= end; j++) {
            if (array[j] < pivot) {
                swap(array, i, j);
                i++;
            }
        }
        array[i] = pivot;
        return i;
    }

    public static void main(String[] args) {
        new QuickSort().simpleTest();
        new QuickSort().largeDataTest();
    }
}
