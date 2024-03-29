package datastructureAndalgorithm.sort;

import java.util.Random;

/**
 * 练习排序使用到的工具类
 *
 * @author <a href="http://doctordeng.vip/">doctordeng</a>
 * @version 1.0
 * @since 2018/9/2 9:59
 */
public class SortUtils {

    public static void checkArguments(int[] array) {
        if (array == null) {
            throw new NullPointerException(" Array must not be null");
        }
    }

    public static void checkArguments(int[] array, int arrayLenth) {
        checkArguments(array);

        if (arrayLenth < 0 || arrayLenth > array.length) {
            throw new IndexOutOfBoundsException("length =" + arrayLenth);
        }
    }

    public static <T> void checkArguments(Comparable<T>[] array) {
        if (array == null) {
            throw new NullPointerException("Array must not be null");
        }
    }

    public static <T> void checkArguments(Comparable<T>[] array, int index) {
        checkArguments(array);
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("index =" + index);
        }
    }

    public static int[] getTestData(int size) {
        return getTestData(0, 10000, size);
    }

    public static int[] getTestData(int min, int max, int size) {
        Random random = new Random();
        int gap = max - min;

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = min + random.nextInt(gap);
        }
        return array;
    }
}
