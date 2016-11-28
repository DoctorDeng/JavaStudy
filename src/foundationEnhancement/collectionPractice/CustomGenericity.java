package foundationEnhancement.collectionPractice;

import java.util.Arrays;

/**
 * Description: 练习自定义泛型的使用
 *
 * @author Doctor邓
 * @since 2016/11/28 21:57
 */
public class CustomGenericity {

    private static <T>T add(T x, T y) {
        return null;
    }

    /**
     * 交换任意数组的两个指定下标的元素
     * @param a 要交换元素的数组
     * @param x 要交换的元素下标
     * @param y 要交换的元素的下标
     * @param <T> 交换后的数组
     */
    private static <T> T[] swap(T[] a, int x, int y) throws IndexOutOfBoundsException {
        if (null == a) {
            return a;
        }

        int length = a.length;
        if (x > length || y > length || x < 0 || y < 0) throw new IndexOutOfBoundsException("数组下标越界");

        if (x == y)  return a;

        T temp = a[x];
        a[x] = a[y];
        a[y] = temp;

        return a;
    }

    public static void main(String[] args) {
        Integer[] a = {1,2,3};
        swap(a, 0, 1);
        System.out.println(Arrays.toString(a));
    }
}
