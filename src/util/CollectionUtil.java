package util;

import java.util.HashSet;
import java.util.Set;

/**
 * 集合操作相关工具类
 * @author denghuajie
 * 
 * @since 2018年6月14日 下午4:40:47
 * 
 */
public class CollectionUtil {
	/**
	 *  求两个 Set 交集：在两集合中都存在的元素
	 */
	public static <T> Set<T> intersection(Set<T> one, Set<T> two) {
		Set<T> result = new HashSet<T>(one);
		result.retainAll(two);
		return result;
	}
	
	/**
	 *  求两个 Set 差集：只在集合一种存在的元素
	 */
	public static <T> Set<T> except(Set<T> one, Set<T> two) {
		Set<T> result = new HashSet<T>(one);
		result.removeAll(two);
		return result;
	}
	
	/**
	 *  求两个 Set 并集：在两集合中所有的元素
	 */
	public static <T> Set<T> union(Set<T> one, Set<T> two) {
		Set<T> result = new HashSet<T>(one);
		result.addAll(two);
		return result;
	}
}
