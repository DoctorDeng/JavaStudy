package datastructureAndalgorithm.sort;

/**
 * 计数排序.
 *
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/4/6 20:04
 * @since 1.0.0
 */
public class CountingSort extends BasicSort {

    @Override
    public void sort(int[] array) {
        checkArguments(array);
        countingSort(array, 0, array.length - 1);
    }

    /**
     * 计数排序（假定待排序数据都为正整数）.
     *
     * @param array 待排序数组
     * @param start 要排序的起始下标
     * @param end   要排序的截至下标
     */
    public static void countingSort(int[] array, int start, int end) {
        checkArguments(array, start, end);

        int size = end - start + 1;
        int max = array[start];
        // 1. 计算待排序数组中元素最大值
        for (int i = start + 1; i <= end; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        // 2. 统计待排序数组中各个元素的个数, 并将其放在统计数组中.
        // 在统计数组中：
        //    index: 待排序元素的值.
        //    index value: 元素个数（在待排序数据中值等同于元素的值的元素个数）.
        int[] elementNumTotal = new int[max + 1];
        for (int i = start; i <= end; i++) {
            int value = array[i];
            // 等同于 elementNumTotal[value] = elementNumTotal[value] + 1;
            elementNumTotal[value]++;
        }

        // 3. 将步骤 2 中统计数组中每个元素的值意义转换为小于或等于下标对应值的元素个数
        // 在统计数组中：
        //    index: 待排序元素的值.
        //    index value: 小于或等于元素值的元素个数, 这里称为 lessOrEqualNum.
        for (int i = 1; i < elementNumTotal.length; i++) {
            elementNumTotal[i] = elementNumTotal[i] + elementNumTotal[i - 1];
        }

        // 4. 排序数组中的元素并将其放入到一个新的数组中，依据原理:
        // 由于已经计算出待排序数组中每个元素对应的小于或等于该元素值的元素个数 lessOrEqualNum
        // 从而可以推断出在已排序情况下每个元素的最大下标不会超过 lessOrEqualNum - 1
        // 然后便可以根据这个规则将待排序元素放入到下标为 lessOrEqualNum - 1 的位置上, 然后将 lessOrEqualNum 数值减 1
        int[] sorted = new int[size];
        for (int i = start; i <= end; i++) {
            int value = array[i];
            int lessOrEqualNum = elementNumTotal[value];
            sorted[lessOrEqualNum - 1] = value;
            elementNumTotal[value] = lessOrEqualNum - 1;
            // 如上代码等同如下代码, 但是上面的性能更高.
            // sorted[elementNumTotal[array[i]] - 1] = array[i];
            // elementNumTotal[array[i]]--;
        }
        // 5. 数据回拷
        System.arraycopy(sorted, 0, array, start, size);
    }

    @Override
    public String getName() {
        return "基数排序";
    }

    public static void main(String[] args) {
        new CountingSort().simpleTest(0, 5);
        new CountingSort().largeDataTest(0, 100);
        BasicSort.test(new CountingSort(), 0, 100, 10000000);
    }
}
