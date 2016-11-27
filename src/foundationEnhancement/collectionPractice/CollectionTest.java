package foundationEnhancement.collectionPractice;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description: 练习 Java 泛型
 *
 * @author Doctor邓
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
    public static void main(String[] args) {
        collectionTest();
    }
}
