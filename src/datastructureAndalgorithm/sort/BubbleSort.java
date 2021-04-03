package datastructureAndalgorithm.sort;

/**
 * 冒泡排序算法的运作如下：
 *   <p/>1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 *   <p/>2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 *   <p/>3.针对所有的元素重复以上的步骤，除了最后一个。
 *   <p/>4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *  <p/>具体信息见：<a href="https://zh.wikipedia.org/wiki/冒泡排序">维基百科-冒泡排序</a>
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @since 2018/9/1 16:40
 */
public class BubbleSort extends BasicSort {

    @Override
    public void sort(int[] array) {
        bestSort(array);
    }

    @Override
    public String getName() {
        return "冒泡排序";
    }

    public static int[] simpleSort(int[] array) {
        checkArguments(array);

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

    /**
     * 在 {@link datastructureAndalgorithm.sort.BubbleSort#simpleSort(int[])} 的基础上优化的冒泡排序算法
     * 采用了数组有序标志位，当数据有序时通过标志位提前跳出排序循环
     */
    public static int[] optimizationSort(int[] array) {
        checkArguments(array);

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

    /**
     * 在 {@link datastructureAndalgorithm.sort.BubbleSort#optimizationSort(int[])} 的基础上再次优化的冒泡排序算法
     * 在排序时界定数组有序边界（即数组从边界开始至右所有元素已全部有序），然后在排序时比较到边界即可
     */
    public static int[] bestSort(int[] array) {
        checkArguments(array);

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
}
