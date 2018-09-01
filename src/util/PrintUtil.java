package util;

/**
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @description 打印工具类
 * @since 2018/9/1 16:44
 */
public class PrintUtil {
    public static void print(int[] array) {
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
