package datastructureAndalgorithm.sort;

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
        recursionImpl(array, 0, array.length -1);
    }

    // 基于递归实现
    private void recursionImpl(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int pivotIndex = partition(array, startIndex, endIndex);
        recursionImpl(array, startIndex, pivotIndex -1);
        recursionImpl(array, pivotIndex, endIndex);
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
        new QuickSort().simpleTest();
    }
}
