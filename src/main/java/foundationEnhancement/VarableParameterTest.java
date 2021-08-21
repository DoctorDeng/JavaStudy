package foundationEnhancement;

import java.util.Arrays;

/**
 * 练习 Java 可变参数
 * Created by doctordeng on 2016/11/20.
 */
public class VarableParameterTest {

    public static void varableParameterTset(int x, int... args) {
        int[] temp = args;
        int result = 0;

        for (int i = 0; i < temp.length; i++) {
            result += temp[i];
        }
        result = (int) (result / x);
        System.out.print(x + "除" + Arrays.toString(temp) + "相加的结果约等于为：" + result);
        System.out.println();
    }

    public static void main(String[] args) {
        //varableParameterTset(3, 10, 3, 4, 5);
        Integer a = Integer.valueOf(127);
        Integer b = Integer.valueOf(127);
        System.out.println(a == b);
    }
}
