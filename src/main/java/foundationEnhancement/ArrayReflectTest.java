package foundationEnhancement;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class ArrayReflectTest {
    public static void arrayTest() {
        Class cla1 = (new String[5]).getClass();
        Class cla2 = (new String[4]).getClass();
        Class cla3 = (new int[4]).getClass();
        Class cla4 = (new String[4]).getClass();
        System.out.println(cla1 == cla2);
        System.out.println(cla2 == cla3);
        System.out.println(cla2 == cla4);
        System.out.println(cla1.getName());
        System.out.println(cla3.getName());
    }
    public static void printArrayConent() {
        int[] a     = new int[]{1 , 3};
        String[] b  = new String[]{"22", "44", "55"};
        System.out.println(a);
        System.out.println(b);
        System.out.println(Arrays.toString(a));
    }
    // 练习反射数组
    public static void reflectArray(Object array) {
        if (array == null) {
            System.out.println("null");
        }

        Class<?> clas = array.getClass();

        if (clas.isArray()) {
            int length = Array.getLength(array);
            int iMax = length - 1;
            if (iMax == -1)
                System.out.println("[]");

            StringBuilder sb = new StringBuilder();
            sb.append("[");

            for (int i = 0; ; i++) {
                sb.append(Array.get(array, i));
                if (i == iMax) {
                    sb.append("]");
                    break;
                }
                sb.append(", ");
            }
            System.out.println(sb.toString());
        } else {
            System.out.println(array.toString());
        }
    }

    /**
     * ArrayList 和 HashSet 的区别
     */
    public static void collectionTest() {
        CollectionTest a = new CollectionTest("1", "2");
        CollectionTest b = new CollectionTest("1", "3");
        CollectionTest c = new CollectionTest("1", "2");

        Collection<CollectionTest> collections = new HashSet<>();
        collections.add(a);
        collections.add(b);
        collections.add(c);

        //a.x = 100;
        //true  1
        //false 2
        boolean isRemove = collections.remove(a);
        System.out.println(isRemove);
        System.out.println(collections.size());
    }
	public static void main(String[] args) {
        printArrayConent();
        reflectArray(new String[]{"dsd","dsdsd","df"});
        collectionTest();
	}
}
class CollectionTest {
    public int x = 10;
    public int y = 21;

    private String a;
    private String b;

    public CollectionTest(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectionTest that = (CollectionTest) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        if (a != null ? !a.equals(that.a) : that.a != null) return false;
        return b != null ? b.equals(that.b) : that.b == null;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
