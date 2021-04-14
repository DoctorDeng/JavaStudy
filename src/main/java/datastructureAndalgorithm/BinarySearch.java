package datastructureAndalgorithm;

import datastructureAndalgorithm.sort.SortUtils;

/**
 * 二分查找练习.
 * @author <a href="http://doctordeng.github.io">DoctorDeng</a>
 * @date 2021/4/14 20:24
 * @since 1.0.0
 */
public class BinarySearch {

    /**
     * 查询指定数组中指定值的下标.
     * @param array 已排序且没有重复值的数组
     * @param value 待查找的值
     * @return 待查找值在数组中的下标, 如果该值在数组中不存在返回 -1
     */
    public static int simpleBinarySearch(int[] array, int value) {
        SortUtils.checkArguments(array);
        int start = 0;
        int end = array.length - 1;
        while (end >= start) {
            // 等同于 mid = start + (end - start)/2
            int mid = start + ((end - start) >> 1);
            int midValue = array[mid];
            if (value > midValue) {
                start = mid + 1;
            } else if (value < midValue) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
