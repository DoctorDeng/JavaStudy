package datastructureAndalgorithm.sort;

import datastructureAndalgorithm.utils.SortUtil;
import util.PrintUtil;

/**
 * 插入排序练习.
 *
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/3/29 19:35
 * @since 1.0.0
 */
public class InsertionSort {

    public static void sort(int[] array, int arrayLength) {
        SortUtil.checkArguments(array, arrayLength);

        for (int i = 1; i < arrayLength; i++) {
            int noSortValue = array[i];

            int j = i - 1;
            // 将未排序元素插入到已排序集合中
            for (; j >= 0; j--) {
                if (array[j] > noSortValue) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = noSortValue;
        }
    }

    public static void main(String[] args) {
        int[] testData = SortUtil.getTestData(10);
        PrintUtil.print(testData);
        sort(testData, 10);
        PrintUtil.print(testData);
    }
}
