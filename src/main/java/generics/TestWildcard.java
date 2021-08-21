package generics;

/**
 * @author <a href="http://doctordeng.vip/">doctordeng</a>
 * @version 1.0
 * @description 练习泛型通配符
 * @since 2017/10/17 21:27
 */
public class TestWildcard {
    public static void main(String[] args) {
        Manager ceo = new Manager();
        Manager cfo = new Manager();
        A<Manager> a = new A<>(ceo, cfo);
        A<? extends Employee> b = a;
        // 正确
        Employee aa = b.getFirst();
        // 错误
        //b.setFirst(ceo);
        A<? super Manager> bb = new A<>();
        // 正确
        bb.setFirst(ceo);
        Employee employee = new Employee();
        // 错误
        // bb.setFirst(employee);
        // 错误
        //Manager bbb = bb.getFirst();
        // 错误
        //Employee bbb = bb.getFirst();
        CTOManager ctoManager = new CTOManager();
        // 正确
        bb.setFirst(ctoManager);
    }
}
class A<T> {
    private T first;
    private T second;
    public A(){}

    public A(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
class Employee {

}
class Manager extends Employee {

}
class CTOManager extends  Manager {

}