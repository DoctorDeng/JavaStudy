package reference;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @description
 * @since 2018/9/20 12:45
 */
public class WeakReferenceTest {
    // 会触发 OOM
    private static void weakReferenceTestWithOOM() {
        List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();

        for (int i = 0; i < 1000; i++) {
            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
            d.put(new byte[1000][1000], new byte[1000][1000]);
            maps.add(d);
            System.gc();
            System.err.println("i:" + i);
        }
    }

    /**
     * 由于 WeakHashMap的键对象为弱引用，因此当发生GC时键对象所指向的内存空间将被回收，
     * 被回收后再调用 size、clear 或 put等直接或间接调用私有 expungeStaleEntries方法的实例方法时，
     * 则这些键对象已被回收的项目（Entry）将被移除出键值对集合中。
     */
    private static void weakReferenceTest() {
        List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();

        for (int i = 0; i < 1000; i++) {
            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
            d.put(new byte[1000][1000], new byte[1000][1000]);
            maps.add(d);
            System.gc();
            System.err.println("i:" + i);

            for (int j = 0; j < i; j++) {
                // 触发移除Entry操作, 即 size 为 0
                System.err.println(j+  " size:" + maps.get(j).size());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        WeakReferenceTest.weakReferenceTestWithOOM();
    }
}
