package datastructureAndalgorithm.utils;


import org.springframework.util.Assert;

import java.util.Iterator;

/**
 * 迭代器工具类
 *
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/1/18 21:06
 * @since 1.0.0
 */
public class IterableUtils {

    public static <T extends Iterable<?>> String toString(T iterable) {
        if (iterable != null) {
            Iterator<?> iterator = iterable.iterator();
            StringBuilder sb = new StringBuilder();
            if (iterator.hasNext()) {
                for (Object value : iterable) {
                    sb.append("{").append(value.toString()).append("},");
                }
            } else {
                sb.append("{},");
            }
            return sb.substring(0, sb.lastIndexOf(","));
        }
        return null;
    }

    public static <V, T extends Iterable<V>> boolean containsValue(V target, T iterable) {
        Assert.notNull(iterable, "Iterable must not be null");
        Iterator<V> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            for (V value : iterable) {
                if (equals(value, target)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <V, T extends Iterable<V>> boolean remove(V target, T iterable) {
        Assert.notNull(iterable, "Iterable must not be null");
        boolean removed = false;
        Iterator<V> iterator = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        if (iterator.hasNext()) {
            while (iterator.hasNext()) {
                V value = iterator.next();
                if (equals(value, target)) {
                    iterator.remove();
                    removed = true;
                }
            }
        }
        return removed;
    }

    private static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 != null && o2 != null) {
            return o1.equals(o2);
        }
        return false;
    }
}
