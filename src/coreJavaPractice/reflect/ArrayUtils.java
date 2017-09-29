package coreJavaPractice.reflect;

import java.lang.reflect.Array;

/**
 * 数组工具类
 * @author Doctor邓
 *
 */
public class ArrayUtils {

	@SuppressWarnings("unchecked")
	public static <T> T[] arrayCopy(T[] original, int newLength) {
		return (T[]) copyOf(original, newLength, original.getClass());
	}
	
	public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
		@SuppressWarnings("unchecked")
		// 这里做强转的原因是不同类型的数组是不能直接比较的
		T[] copy = ((Object) newType == (Object)Object[].class)
				? (T[]) new Object[newLength]
			    : (T[]) Array.newInstance(newType.getComponentType(), newLength);
		
		System.arraycopy(original, 0, copy, 0, 
						 Math.min(original.length, newLength));
		return copy;
	}
	
	
	public static void main(String[] args) {
		Integer[] i = {1,2,3};
		System.out.println(ArrayUtils.arrayCopy(i, 100)[1]);
		// 两种不同的数组类型无法直接比较, 必须要由 xx extends ss 的关系才能够直接比较
		Double[] j = {4.8,5.1,6.1};
		// 所以这里要做一步强制转换
		System.out.println((Object)i.getClass() == j.getClass());
	}
}
