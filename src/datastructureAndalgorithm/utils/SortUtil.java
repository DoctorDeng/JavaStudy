package datastructureAndalgorithm.utils;

import java.util.Random;

/**
 * 练习排序使用到的工具类
 *
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @since 2018/9/2 9:59
 */
public class SortUtil {
    public static void checkArgument(int[] array) {
        if (array == null) {
            throw new NullPointerException(" Array must not be null");
        }
    }

    public static void checkArguments(int[] array, int arrayLenth) {
        checkArgument(array);

        if (arrayLenth < 0 || arrayLenth > array.length) {
            throw new IndexOutOfBoundsException("index =" + arrayLenth);
        }
    }

    public static int[] getTestData(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }
}
