package datastructureAndalgorithm.sort;

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
        //quickSortByRecursion(array, 0, array.length -1);
        quickSortByLoop(array, 0, array.length -1);
    }

    /**
     * 对指定数组指定下标范围进行排序.
     * <p>算法为基于递归实现的快速排序算法, 未做任何优化, 有堆栈溢出风险.
     * @param array 待排序数组
     * @param startIndex  起始下标
     * @param endIndex    结尾下标
     */
    private void quickSortByRecursion(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int pivotIndex = partition(array, startIndex, endIndex);
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
    private void quickSortByLoop(int[] array, int startIndex, int endIndex) {
        Stack<Integer> stack = new Stack<>();

        stack.push(startIndex);
        stack.push(endIndex);

        while (!stack.isEmpty()) {
            int _endIdx = stack.pop();
            int _startIdx =  stack.pop();

            int pivotIndex = partition(array, _startIdx, _endIdx);

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


    private int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (array[j] < pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, end);
        return i;
    }


    public static void main(String[] args) {
        //new QuickSort().simpleTest();
        new QuickSort().largeDataTest();
    }
}
