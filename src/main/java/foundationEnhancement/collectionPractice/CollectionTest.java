package foundationEnhancement.collectionPractice;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description: 练习 Java 泛型
 *
 * @author doctordeng
 * @since 2016/11/27 18:17
 */
public class CollectionTest {
    public static void collectionTest() {
        Collection<String> collection = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        // 值为 true, 因为泛型指针对编译阶段
        System.out.println(collection.getClass() == list.getClass());

        list.add(2);
        try {
            // 使用反射的方式来向集合中添加不是 Integer 的值可以添加进去
            list.getClass().getMethod("add", Object.class).invoke(list, "abc");
            System.out.println(list.size());
            System.out.println(list.get(1));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void printOutCollection(Collection<?> collection) {
        if (null == collection) {
            System.out.println("null");
        }

        for (Object obj:collection) {
            System.out.println(obj);
        }
    }

    /**
     * 练习 泛型通配符
     */
    public static void wildcard() {
        Class<?> y;
        Class<String> x = String.class;
        // 通配符可以等于具体的类型，但是具体的类型却不能成为通配符：
        //x = y; //不合法
        y = x; //合法

    }
    public static void main(String[] args) {
        collectionTest();
    }
}
