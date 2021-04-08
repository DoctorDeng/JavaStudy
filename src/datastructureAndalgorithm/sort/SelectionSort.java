package datastructureAndalgorithm.sort;

/**
 * 选择排序.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/4/8 16:44
 */
public class SelectionSort extends BasicSort {
    @Override
    public void sort(int[] array) {
        checkArguments(array);
        selectionSort(array, 0, array.length - 1);
    }

    public static void selectionSort(int[] array, int start, int end) {
        checkArguments(array, start, end);

        for (int i = start; i < end; i++) {
            int minIndex = i;
            for (int j = i + 1; j <= end; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, minIndex, i);
        }
    }

    @Override
    public String getName() {
        return "选择排序";
    }

    public static void main(String[] args) {
        new SelectionSort().simpleTest();
        new SelectionSort().largeDataTest();
    }
}
