package datastructureAndalgorithm;

import datastructureAndalgorithm.sort.SortUtils;

/**
 * 二分查找练习.
 * @author <a href="https://doctordeng.github.io">DoctorDeng</a>
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

    /**
     * 查找指定数组中第一个值与给定元素值相等的元素的下标.
     * @param array 有序数组
     * @param value 给定元素
     * @return 与给定元素相等的第一个元素下标，如果给定元素在数组中不存在返回 -1
     */
    public static int indexOf(int[] array, int value) {
        SortUtils.checkArguments(array);

        int start = 0;
        int end = array.length - 1;
        while (end >= start) {
            int mid = start + ((end - start) >> 1);
            if (value <= array[mid]) {
                end = mid -1;
            } else {
                start = mid + 1;
            }
        }
        if (end + 1 < array.length && array[end + 1] == value ) {
            return end + 1;
        }
        return -1;
    }

    /**
     * 查找指定数组中第一个值与给定元素值相等的元素的下标.
     * <p>功能与 {@link #indexOf(int[], int)} 一样，但是代码更容易让人理解</p>
     * @param array 有序数组
     * @param value 给定元素
     * @return 与给定元素相等的第一个元素下标，如果给定元素在数组中不存在返回 -1
     */
    public static int indexOf2(int[] array, int value) {
        SortUtils.checkArguments(array);
        int start = 0;
        int end = array.length - 1;
        while (end >= start) {
            int mid = start + ((end - start) >> 1);
            int midValue = array[mid];
            if (value < midValue) {
                end = mid - 1;
            } else if (value > midValue) {
                start = mid + 1;
            } else {
                if (mid == 0 || array[mid - 1] != value) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找指定数组中最后一个值与给定元素值相等的元素的下标.
     * @param array 有序数组
     * @param value 给定元素
     * @return 与给定元素相等的最后一个元素下标，如果给定元素在数组中不存在返回 -1
     */
    public static int lastIndexOf(int[] array, int value) {
        SortUtils.checkArguments(array);

        int start = 0;
        int end = array.length - 1;
        while (end >= start) {
            int mid = start + ((end - start) >> 1);
            int midValue = array[mid];
            if (value < midValue) {
                end = mid - 1;
            } else if (value > midValue) {
                start = mid + 1;
            } else {
                if (mid == array.length - 1 || array[mid + 1] != value) {
                    return mid;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找指定数组中第一个大于等于给定元素的元素下标.
     * @param array 有序数组
     * @param value 给定元素
     * @return 第一个大于等于给定元素的元素下标，如果不存在符合条件的元素返回 -1
     */
    public static int equalOrGreaterIndexOf(int[] array, int value) {
        SortUtils.checkArguments(array);
        int start = 0;
        int end = array.length - 1;
        while (end >= start) {
            int mid = start + ((end - start) >> 1);
            int midValue = array[mid];
            if (midValue >= value) {
                if (mid == 0 || array[mid - 1] < value) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找指定数组中最后一个小于等于给定元素的元素下标.
     * @param array 有序数组
     * @param value 给定元素
     * @return 最后一个小于等于给定元素的元素下标，如果不存在符合条件的元素返回 -1
     */
    public static int equalOrLessLastIndexOf(int[] array, int value) {
        SortUtils.checkArguments(array);
        int start = 0;
        int end = array.length - 1;
        while (end >= start) {
            int mid = start + ((end - start) >> 1);
            int midValue = array[mid];
            if (midValue <= value) {
                if (mid == array.length - 1 || array[mid + 1] > value) {
                    return mid;
                } else {
                    start = mid + 1;
                }
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
