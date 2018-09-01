package algorithm.sort;

import util.PrintUtil;

import java.util.Random;

/**
 * 冒泡排序算法的运作如下：
 *   1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 *   2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 *   3.针对所有的元素重复以上的步骤，除了最后一个。
 *   4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *  具体信息见：<a href="https://zh.wikipedia.org/wiki/冒泡排序">维基百科-冒泡排序</a>
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @since 2018/9/1 16:40
 */
public class BubbleSort {

    public static int[] simpleSort(int[] array) {
        if (array == null) {
            throw new NullPointerException(" Array must not be null");
        }

        for (int i = 0, len = array.length; i < len; i++) {

            for (int j = 0; j < len - i - 1; j++) {
                int front = array[j];
                int after = array[j + 1];
                if (front > after) {
                    array[j + 1] = front;
                    array[j] = after;
                }
            }
        }

        return array;
    }
    public static int[] optimizationSort(int[] array) {
        if (array == null) {
            throw new NullPointerException(" Array must not be null");
        }

        for (int i = 0, len = array.length; i < len; i++) {
            /*
             * 数组有序标志, true 有序, false 无序
             * 当数组在排序过程中有序时，通过此标志提前退出循环，减少计算量
             */
            boolean isOrder = true;
            for (int j = 0; j < len - i - 1; j++) {
                int front = array[j];
                int after = array[j + 1];
                if (front > after) {
                    array[j + 1] = front;
                    array[j] = after;
                    isOrder = false;
                }
            }
            // 数组有序时，提前退出循环
            if (isOrder) {
                break;
            }
        }
        return array;
    }

    public static int[] bestSort(int[] array) {
        if (array == null) {
            throw new NullPointerException(" Array must not be null");
        }

        // 数组有序边界下标(即从此边界开始数组已有序)
        int sortBorderIndex = array.length - 1;
        // 最后一次进行元素交换时元素下标
        int lastChangeIndex = 0;

        for (int i = 0, len = array.length; i < len; i++) {
            /*
             * 数组有序标志, true 有序, false 无序
             * 当数组在排序过程中有序时，通过此标志提前退出循环，减少计算量
             */
            boolean isOrder = true;
            for (int j = 0; j < sortBorderIndex; j++) {
                int front = array[j];
                int after = array[j + 1];
                if (front > after) {
                    array[j + 1] = front;
                    array[j] = after;
                    isOrder = false;
                    lastChangeIndex = j;
                }
            }
            // 数组有序时，提前退出循环
            if (isOrder) {
                break;
            }
            /*
             * 数组有序边界下标为最后一次进行元素交换时元素下标
             */
            sortBorderIndex = lastChangeIndex;
        }
        return array;
    }

    public static void main(String[] args) {
       int[] testData = getTestData(10);
       //PrintUtil.print(testData);
       //PrintUtil.print(simpleSort(testData));
        //int[] array = {1,4,2,3,5};
        //PrintUtil.print(bestSort(array));
        //int[] array1 = {1,4,2,3,5};
        //PrintUtil.print(optimizationSort(array1));
    }

    private static int[] getTestData(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }
}
