package foundationEnhancement.collectionPractice;

import foundationEnhancement.Student;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 泛型案例
 * Description: 打印一个 HashMap中的所有 Key 和 Value
 *
 * @author Doctor邓
 * @since 2016/11/28 21:30
 */
public class CollectionPractice {
    public static <K,V> void printOutHashMap(HashMap<K,V> hashMap) {
        Set<K> set = hashMap.keySet();
        Iterator<K> iterable = set.iterator();

        while (iterable.hasNext()) {
            K key = iterable.next();
            V value = hashMap.get(key);
            System.out.println("Key: " + key + "Value: " + value);
        }
    }

    /**
     * 将一个 Object 转换为任意一个类型
     * @param obj
     * @param <T>
     * @return
     */
    private static <T> T autoConvert(Object obj) {
        return (T) obj;
    }

    public <T> void  printOut(List<T> list) {

    }

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "Doctor");
        hashMap.put("age", "20");
        printOutHashMap(hashMap);
        HashMap<String, Student> hashMap1 = new HashMap<>();
        hashMap1.put("Doctor", new Student("Doctor邓", 20, true));
        hashMap1.put("Deng", new Student("Moto", 25, false));
        printOutHashMap(hashMap1);

        Object obj = "abc";
        String str = autoConvert(obj);
        System.out.println(str);
    }
}
