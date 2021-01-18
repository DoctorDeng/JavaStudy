package datastructureAndalgorithm.utils;


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
}
