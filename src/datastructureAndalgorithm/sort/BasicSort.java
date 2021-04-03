package datastructureAndalgorithm.sort;

import util.PrintUtil;

/**
 * 排序基类, 提供排序所用的公共方法.
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/3/31 21:25
 * @since 1.0.0
 */
public abstract class BasicSort implements Sort {

    public static boolean isArraySort(int[] array) {
        checkArguments(array);
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void simpleTest() {
        for (int i = 1; i <= 100; i++) {
            test(this, i);
        }
    }

    public static void test(Sort sort, int size) {
        int[] src = SortUtils.getTestData(size);
        int[] srcCopy = new int[size];
        System.arraycopy(src, 0, srcCopy, 0, size);
        long startTime = System.currentTimeMillis();
        sort.sort(src);
        long endTime = System.currentTimeMillis();
        double timeConsuming = endTime - startTime;
        checkSort(srcCopy, src);
        System.out.printf("排序名称: %s, 共排序 %s 个数据, 耗时 %.2f 毫秒", sort.getName(), size, timeConsuming);
        System.out.println();
    }


    public void largeDataTest() {
        for (int i = 1; i <= 5; i++) {
            test(this, 100000 * i);
        }
    }

    public static void checkSort(int[] array) {
        if (!isArraySort(array)) {
            print("错误数据", array);
            throw new RuntimeException("array sort test failed: array not sort");
        }
    }
    public static void checkSort(int[] src, int[] sorted) {
        if (!isArraySort(sorted)) {
            print("原始数据", src);
            print("错误数据", sorted);
            throw new RuntimeException("array sort test failed: array not sort");
        }
    }

    public static void checkArguments(int[] array) {
        if (array == null) {
            throw new NullPointerException("array must not be null");
        }
    }

    public static void checkArguments(int[] array, int arrayLenth) {
        checkArguments(array);

        if (arrayLenth < 0 || arrayLenth > array.length) {
            throw new IndexOutOfBoundsException("index =" + arrayLenth);
        }
    }

    public static void checkArguments(int[] array, int startIndex, int endIndex) {
        checkArguments(array);
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

    public static void swap(int[] array, int sourceIndex, int targetIndex) {
        if (sourceIndex == targetIndex) {
            return;
        }
        int source = array[sourceIndex];
        array[sourceIndex] = array[targetIndex];
        array[targetIndex] = source;
    }

    public static <T> void swap(Comparable<T>[] array, int sourceIndex, int targetIndex) {
        if (sourceIndex == targetIndex) {
            return;
        }
        Comparable<T> source = array[sourceIndex];
        array[sourceIndex] = array[targetIndex];
        array[targetIndex] = source;
    }

    public static <T> boolean less(Comparable c, Comparable v) {
        if (c == null || v == null) {
            throw new NullPointerException("Comparable must not be null");
        }
        return c.compareTo(v) < 0;
    }

    public static void print(int[] array) {
        PrintUtil.print(array);
    }

    public static void print(String name, int[] array) {
        System.out.print(name + ", 数据量 (" + array.length + ") 详细信息: ") ;
        PrintUtil.print(array);
    }
}
