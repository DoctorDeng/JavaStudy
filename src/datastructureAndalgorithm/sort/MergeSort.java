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
        mergeSort(array, 0, array.length -1);
    }

    private void mergeSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end)/2;

        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);

        merge(array, start, mid, end);
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
