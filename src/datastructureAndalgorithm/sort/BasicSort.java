package datastructureAndalgorithm.sort;

import datastructureAndalgorithm.utils.SortUtil;
import util.PrintUtil;

/**
 * 排序基类, 提供排序所用的公共方法.
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/3/31 21:25
 * @since 1.0.0
 */
public abstract class BasicSort {

    public abstract void sort(int[] array);

    public boolean isArraySort(int[] array) {
        checkArgument(array);
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void simpleTest() {
        int[] array = SortUtil.getTestData(15);
        print(array);
        sort(array);
        print(array);
        if (!isArraySort(array)) {
            throw new RuntimeException("array sort test failed: array not sort");
        }
    }

    public void largeDataTest() {
        long startTime = System.currentTimeMillis();
        int testDataSize = 100000;
        int[] array = SortUtil.getTestData(testDataSize);
        sort(array);
        long endTime = System.currentTimeMillis();
        double timeConsuming = endTime - startTime;
        System.out.printf("%s 个数据, 耗时 %.5f 毫秒", testDataSize, timeConsuming);
    }



    public void checkArgument(int[] array) {
        if (array == null) {
            throw new NullPointerException("array must not be null");
        }
    }

    public void checkArguments(int[] array, int arrayLenth) {
        checkArgument(array);

        if (arrayLenth < 0 || arrayLenth > array.length) {
            throw new IndexOutOfBoundsException("index =" + arrayLenth);
        }
    }

    public void checkArguments(int[] array, int startIndex, int endIndex) {
        checkArgument(array);
        if (startIndex < 0) {
            throw new IndexOutOfBoundsException("index =" + startIndex);
        }
        if (endIndex > array.length -1) {
            throw new IndexOutOfBoundsException("index =" + endIndex);
        }
        if (startIndex > endIndex) {
            throw new IndexOutOfBoundsException("start index =" + startIndex + ", end index =" + endIndex);
        }
    }

    public void swap(int[] array, int sourceIndex, int targetIndex) {
        if (sourceIndex == targetIndex) {
            return;
        }
        int source = array[sourceIndex];
        array[sourceIndex] = array[targetIndex];
        array[targetIndex] = source;
    }

    public void print(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        for (int i = 0, len = array.length; i < len; i++) {
            if (i == 0) {
                System.out.print("[");
            }
            System.out.print(array[i]);
            if (i != len - 1) {
                System.out.print(",");
            }
            if (i == len - 1) {
                System.out.print("]");
            }
        }
        System.out.println();
    }

}
