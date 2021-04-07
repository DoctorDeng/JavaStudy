package datastructureAndalgorithm.sort;

import util.PrintUtil;

/**
 * 基数排序.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/4/7 16:08
 */
public class RadixSort extends BasicSort {
    @Override
    public void sort(int[] array) {
        checkArguments(array);
        radixSort(array, 0, array.length - 1);
    }

    /**
     * 基数排序（假设数组元素都为正整数，对于负数排序待后续优化）.
     * <p>基数排序核心思想是对待排序元素从低位到高位（无相应位数补 0）进行计数排序，最终便能够将整个数组进行排序</p>
     *
     * @param array 待排序数组
     * @param start 要排序的起始下标
     * @param end   要排序的截至下标
     */
    public static void radixSort(int[] array, int start, int end) {
        checkArguments(array, start, end);

        int max = getMax(array, start, end);
        // 从个位开始排序, 一直到最大值的最高位
        for (int digit = 1; max / digit != 0; digit = digit * 10) {
            countingSort(array, start, end, digit);
        }
    }

    private static int getMax(int[] array, int start, int end) {
        int max = Math.abs(array[start]);
        for (int i = start + 1; i <= end; i ++) {
            int value = Math.abs(array[i]);
            if (max < value) {
                max = value;
            }
        }
        return max;
    }

    private static void countingSort(int[] array, int start, int end, int digit) {
        // 对于整数, 每位（个位、百位、千位。。。）的可能值都为 0~9, 考虑到负数情况统计数组大小应该为 20 (-9 ~ 9)
        int numTotalSize = 20;
        int size = end - start + 1;
        int[] numTotal = new int[numTotalSize];

        for (int i = start; i <= end; i++) {
            int value = array[i];
            // 对元素对应位数的值
            int digitValue = (value / digit) % 10 + 10;
            numTotal[digitValue]++;
        }

        for (int i = 1; i < numTotalSize; i++) {
            numTotal[i] = numTotal[i - 1] + numTotal[i];
        }

        int[] sorted = new int[size];
        for (int i = end; i >= start; i--) {
            int value = array[i];
            // 对元素对应位数的值
            int digitValue = (value / digit) % 10 + 10;
            int index = numTotal[digitValue] - 1;
            sorted[index] = value;
            numTotal[digitValue] = index;
        }
        System.arraycopy(sorted, 0, array, start, size);
    }

    @Override
    public String getName() {
        return "基数排序";
    }

    public static void main(String[] args) {
        new RadixSort().simpleTest(-100, 10);
        new RadixSort().largeDataTest(-200, 100);
        BasicSort.test(new RadixSort(), 100000000, 200000000, 10000000);
    }
}
