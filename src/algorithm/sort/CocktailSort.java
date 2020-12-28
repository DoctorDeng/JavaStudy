package algorithm.sort;

import algorithm.utils.SortUtil;
import util.PrintUtil;
/**
 * 鸡尾酒排序：
 *   鸡尾酒排序，也就是定向冒泡排序，鸡尾酒搅拌排序，搅拌排序（也可以视作选择排序的一种变形），涟漪排序，来回排序或快乐小时排序，是冒泡排序的一种变形。
 *   此算法与冒泡排序的不同处在于排序时是以双向在序列中进行排序。
 * <br/>
 *   鸡尾酒排序等于是冒泡排序的轻微变形。不同的地方在于从低到高然后从高到低，而冒泡排序则仅从低到高去比较序列里的每个元素。
 *   它可以得到比冒泡排序稍微好一点的性能，原因是冒泡排序只从一个方向进行比对（由低到高），每次循环只移动一个项目。
 * <br/>
 *   鸡尾酒排序最糟或是平均所花费的次数都是 O(n^2)，但如果序列在一开始已经大部分排序过的话，会接近 O(n)
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @since 2018/9/2 9:43
 */
public class CocktailSort {
    /**
     * 鸡尾酒排序，通过数组有序标志来提前退出排序循环
     */
    public static int[] sort(int[] array) {
        SortUtil.checkArgument(array);

        int rightIndex = array.length - 1;
        int leftIndex  = 0;

        // 数组是否有序标志
        boolean isOrder = true;

        for (int i = 0, len = array.length; i < len; i++) {
            if (leftIndex > rightIndex) {
                break;
            }

            // 右排
            for (int j = leftIndex; j < rightIndex; j++) {
                int left  = array[j];
                int right = array[j+1];
                if (left > right) {
                    array[j]   = right;
                    array[j+1] = left;
                    isOrder = false;
                }
            }
            if (isOrder) {
                break;
            }
            rightIndex--;

            isOrder = true;
            // 左排
            for (int j = rightIndex; j > leftIndex; j--) {
                int left  = array[j-1];
                int right = array[j];
                if (right < left) {
                    array[j]   = left;
                    array[j-1] = right;
                    isOrder = false;
                }
            }
            if (isOrder) {
                break;
            }
            leftIndex++;
        }
        return array;
    }
    /**
     * 优化后的鸡尾酒排序，同 {@link algorithm.sort.BubbleSort#bestSort(int[])} 优化手法一致
     */
    public static int[] optimizationSort(int[] array) {
        SortUtil.checkArgument(array);
        // 数组从左 -> 右有序左边界下标
        int rightOrderIndex = array.length - 1;
        // 数组从右 -> 左有序右边界下标
        int leftOrderIndex  = 0;

        // 右排最后一次发生元素交换下标，以此确定 rightOrderIndex
        int lastRightChangeIndex = 0;
        // 左排最后一次发生元素交换下标，以此确定 leftOrderIndex
        int lastLeftChangeIndex  = 0;

        // 数组是否有序标志
        boolean isOrder = true;

        for (int i = 0, len = array.length; i < len; i++) {
            // 右排
            for (int j = leftOrderIndex; j < rightOrderIndex; j++) {
                int left  = array[j];
                int right = array[j+1];
                if (left > right) {
                    array[j]   = right;
                    array[j+1] = left;
                    isOrder = false;
                    lastRightChangeIndex = j;
                }

            }
            rightOrderIndex = lastRightChangeIndex;
            if (isOrder) {
                break;
            }

            isOrder = true;
            // 左排
            for (int j = rightOrderIndex; j > leftOrderIndex; j--) {
                int left  = array[j-1];
                int right = array[j];
                if (right < left) {
                    array[j]   = left;
                    array[j-1] = right;
                    lastLeftChangeIndex = j;
                    isOrder = false;
                }
            }
            if (isOrder) {
                break;
            }
            leftOrderIndex = lastLeftChangeIndex;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] testData = SortUtil.getTestData(10);
        PrintUtil.print(testData);
        //PrintUtil.print(sort(testData));
        // 鸡尾酒排序和优化后的冒泡排序对比，结果：鸡尾酒排序效率更高
       /* int[] array = {1,4,2,3,5};
        PrintUtil.print(BubbleSort.optimizationSort(array));
        int[] array1 = {1,4,2,3,5};
        PrintUtil.print(sort(array1));*/


        //PrintUtil.print(optimizationSort(testData));
        // 优化后的鸡尾酒排序和最优冒泡排序对比，结果：优化后的鸡尾酒排序效率更高
        int[] array = {1,4,3,2,5};
        PrintUtil.print(BubbleSort.bestSort(array));
        int[] array1 = {1,4,3,2,5};
        PrintUtil.print(optimizationSort(array1));
    }
}
