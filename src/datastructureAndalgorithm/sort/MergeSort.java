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
        checkArgument(array);
        mergeSortByLoop(array, 0, array.length -1);
        //mergeSort(array, 0, array.length -1);
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
        int range = 2;
        int length = end - start + 1;

        while (range <= length) {
            for (int i = 0; i <= length; i++) {
                int _start = start + i * range;
                if (_start > end) {
                    break;
                }
                int _end = _start + range - 1;
                if (_end > end) {
                    _end = end;
                }
                int mid = (_start + _end) / 2;
                merge(array, _start, mid, _end);
            }
            range = range * 2;
        }
        merge(array, start, (end + start)/2, end);
    }


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

    public static void main(String[] args) {
        //new MergeSort().simpleTest();
        new MergeSort().largeDataTest();
    }

}
