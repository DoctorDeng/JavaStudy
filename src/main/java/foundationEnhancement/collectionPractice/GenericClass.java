package foundationEnhancement.collectionPractice;

import foundationEnhancement.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 练习 Java 泛型类
 *     在一个类的实例对象中多处都要用到同一个泛型, 即这些地方引用的泛型要保持
 *     同一个实际类型时, 这时候要采用泛型类型的方式进行定义, 也就是类级别的泛型
 *     可以参考 ArrayList 等泛型集合
 * @author doctordeng
 * @since 2016/11/29 21:17
 */
public class GenericClass<T> {
    Object[] objects = new Object[10];
    public void add(T t){
        for (int i = 0, len = objects.length; i < len; i++) {
            if (objects[i] == null) {
                objects[i] = t;
            }
        }
    }
    public T get(int index){
        return (T)objects[index];
    }

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
    }
}
