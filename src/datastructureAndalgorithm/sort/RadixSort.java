package datastructureAndalgorithm.sort;

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
     *
     * @param array 待排序数组
     * @param start 要排序的起始下标
     * @param end   要排序的截至下标
     */
    public static void radixSort(int[] array, int start, int end) {
        checkArguments(array, start, end);

        int max = getMax(array, start, end);

        for (int digit = 1; max / digit != 0; digit = digit * 10) {
            countingSort(array, start, end, digit);
        }
    }

    private static int getMax(int[] array, int start, int end) {
        int max = array[start];
        for (int i = start + 1; i <= end; i ++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    private static void countingSort(int[] array, int start, int end, int digit) {
        int numTotalSize = 10;
        int size = end - start + 1;
        int[] numTotal = new int[numTotalSize];

        for (int i = start; i <= end; i++) {
            int value = array[start];
            // 对元素对应位数的值
            int digitValue = (value / digit) % 10;
            numTotal[digitValue]++;
        }

        for (int i = 1; i < numTotalSize; i++) {
            numTotal[i] = numTotal[i - 1] + numTotal[i];
        }

        int[] sorted = new int[size];

        for (int i = start; i <= end; i++) {
            int value = array[start];
            // 对元素对应位数的值
            int digitValue = (value / digit) % 10;
            int num = numTotal[digitValue] - 1;
            sorted[num] = value;
            numTotal[digitValue] = num;
        }
        System.arraycopy(sorted, 0, array, start, size);
    }

    @Override
    public String getName() {
        return "基数排序";
    }

    public static void main(String[] args) {
        new RadixSort().simpleTest(0, 5);
        new RadixSort().largeDataTest(0, 100);
        BasicSort.test(new RadixSort(), 100000000, 200000000, 10000000);
    }
}
