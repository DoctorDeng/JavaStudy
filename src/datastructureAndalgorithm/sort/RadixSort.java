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
        radixSort(array, 0, array.length);
    }

    /**
     * 基数排序.
     *
     * @param array 待排序数组
     * @param start 要排序的起始下标
     * @param end   要排序的截至下标
     */
    public static void radixSort(int[] array, int start, int end) {
        checkArguments(array, start, end);

        int maxDigit = getMaxDigit(array, start, end);

        int[][] buckets = new int[0][0];

    }

    private static int getMaxDigit(int[] array, int start, int end) {
        int max = array[start];
        for (int i = start + 1; i <= end; i ++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        if (max == 0) {
            return 1;
        }
        int i = 0;
        for (; max != 0; max = max / 10 ) {
            i++;
        }
        return i;
    }

    @Override
    public String getName() {
        return "基数排序";
    }
}
