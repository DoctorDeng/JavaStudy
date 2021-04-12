package datastructureAndalgorithm.sort;

/**
 * 插入排序练习.
 *
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/3/29 19:35
 * @since 1.0.0
 */
public class InsertionSort extends BasicSort {

    @Override
    public void sort(int[] array) {
        insertionSort(array, array.length);
    }

    @Override
    public String getName() {
        return "插入排序";
    }

    public static void insertionSort(int[] array, int arrayLength) {
        checkArguments(array, arrayLength);

        // 将未排序元素依次插入到已排序序列
        for (int i = 1; i < arrayLength; i++) {
            int noSortValue = array[i];
            // 将未排序元素与已排序元素逐个比较
            // 备注：从右往左遍历，保证稳定性
            for (int j = i - 1; j >= 0; j--) {
                // 当未排序元素比已排序元素小，已排序元素右移
                if (array[j] > noSortValue) {
                    array[j + 1] = array[j];
                }
                // 当未排序元素比已排序元素大，结束遍历
                else {
                    // 插入未排序元素到正确位置
                    array[j + 1] = noSortValue;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        new InsertionSort().simpleTest();
        new InsertionSort().largeDataTest();
    }
}
