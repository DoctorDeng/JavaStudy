/**
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @description
 * @since 2017/10/24 20:23
 */
public class TestChildAndParent {
    public static void main(String[] args) {
        B b = new B();
        b.test();
    }
}

class A {
    protected String aaa = "sss";

    public void test() {
        aaa = "aaa";
    }
}
class B extends A {
    @Override
    public void test() {
        System.out.println(aaa);
        System.out.println(super.aaa);
        super.test();
        System.out.println(aaa);
        aaa = "ccc";
        System.out.println(aaa);
    }
}
