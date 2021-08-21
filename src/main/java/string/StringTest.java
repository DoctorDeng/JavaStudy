package string;

/**
 * @author <a href="http://doctordeng.vip/">doctordeng</a>
 * @version 1.0
 * @description 练习 String 的 Immutable(不可变) 机制
 * @since 2018/9/19 11:08
 */
public class StringTest {

    /**
     * 测试 String 常量池(constant pool)
     */
    private static void defaultTest() {
        String s0="kvill";
        String s1="kvill";
        String s2="kv" + "ill";
        String s3 = new String("kvill");
        String s4 = new String("kv") + "ill";
        String s5 = "kv";
        String s6 = "ill";
        String s7 = s5 + s6;
        System.out.println( s0==s1 ); // true
        System.out.println( s0==s2 ); // true
        System.out.println( s0==s3 ); // false
        System.out.println( s0==s4 ); // false
        System.out.println( s0==s7 ); // false
    }

    private static void internTest() {
        String str1 = "fly";
        String str2 = "weight";
        String str3 = "flyweight";
        String str4 = str1 + str2;

        System.out.println(str3 == str4);

        str4 = (str1 + str2).intern();
        System.out.println(str3 == str4);
    }


    public static void main(String[] args) {
        //StringTest.defaultTest();
       StringTest.internTest();
    }
}
