package base.loop;

/**
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @description 练习使用标识跳出外层 For 循环
 * @since 2018/9/20 16:15
 */
public class ForLoopTest {
    public static void main(String[] args) {
        ok:
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("i:" + i + " j:" + j);
                if (j == 3 && i == 3) {
                    break ok;
                }
            }
        }
    }
}
