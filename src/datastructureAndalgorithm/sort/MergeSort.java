package datastructureAndalgorithm.sort;

/**
 * 归并排序.
 *
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/4/2 19:16
 * @since 1.0.0
 */
public class MergeSort extends BasicSort {
    @Override
    public void sort(int[] array) {
        checkArguments(array);
        mergeSortByLoop(array, 0, array.length -1);
        //mergeSort(array, 0, array.length -1);
    }

    @Override
    public String getName() {
        return "归并排序";
    }

    /**
     * 对指定数组指定下标范围进行排序.
     * <p>算法采用归并排序(基于递归的自顶向下思路实现).
     * @param array 待排序数组
     * @param start  起始下标
     * @param end    结尾下标
     */
    private void mergeSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end)/2;

        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);

        merge(array, start, mid, end);
    }
    /**
     * 对指定数组指定下标范围进行排序.
     * <p>算法采用归并排序(基于循环的自底向上的思路实现).
     * @param array 待排序数组
     * @param start  起始下标
     * @param end    结尾下标
     */
    private void mergeSortByLoop(int[] array, int start, int end) {
        int length = end - start + 1;
        int[] temp = new int[array.length];
        for (int range = 1; range < length; range = range + range) {
            for (int _start = start; _start <= end; _start += range + range) {
                int _end = _start + range + range - 1;
                if (_end > end) {
                    _end = end;
                }
                mergeInPlace(array, temp, _start, _start + range -1, _end);
            }
        }
    }

    /**
     * 将数组下标 start ~ mid 和 mid + 1 ~ end 两范围内的数据进行归并排序，每次归并都会创建临时数组.
     * @param array 待归并数组
     * @param start 起始下标
     * @param mid   中间下标
     * @param end   结尾下标
     */
    private void merge(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];

        int leftCursor = start;
        int rightCursor = mid + 1;
        int tempCursor = 0;

        while (leftCursor <= mid && rightCursor <= end) {
            if (array[leftCursor] <= array[rightCursor]) {
                temp[tempCursor++] = array[leftCursor++];
            } else {
                temp[tempCursor++] = array[rightCursor++];
            }
        }
        // 拷贝子数组临时数据
        while (leftCursor <= mid) {
            temp[tempCursor++] = array[leftCursor++];
        }
        while (rightCursor <= end) {
            temp[tempCursor++] = array[rightCursor++];
        }
        // 排序后的数据拷贝回原数组
        System.arraycopy(temp, 0, array, start, temp.length);
    }

    /**
     * 将数组下标 start ~ mid 和 mid + 1 ~ end 两范围内的数据进行归并排序，基于原地归并实现.
     * @param array 待归并数组
     * @param temp  临时数组, 大小应与 array 一样
     * @param start 起始下标
     * @param mid   中间下标
     * @param end   结尾下标
     */
    private void mergeInPlace(int[] array, int[] temp, int start, int mid, int end) {
        int leftCursor = start;
        int rightCursor = mid + 1;
        // 1. 复制待排序数据到临时数组中
        System.arraycopy(array, start, temp, start, end - start + 1);
        // 2. 进行归并排序并直接将数据复制回原数组
        for (int i = start; i <= end; i++) {
            if (leftCursor > mid) {
                array[i] = temp[rightCursor++];
            } else if (rightCursor > end) {
                array[i] = temp[leftCursor++];
            } else if (temp[leftCursor] <= temp[rightCursor]) {
                array[i] = temp[leftCursor++];
            } else {
                array[i] = temp[rightCursor++];
            }
        }
    }

    public static void main(String[] args) {
        new MergeSort().simpleTest();
        new MergeSort().largeDataTest();
    }

}
