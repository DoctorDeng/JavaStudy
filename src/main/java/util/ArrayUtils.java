package util;

/**
 * 数组工具类.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2022/1/11 16:54
 */
public abstract class ArrayUtils {

	private ArrayUtils() {
	}

	/**
	 * 合并多个数组, 注意该方法并没有对数组是否为 null 进行校验.
	 *
	 * @param bytesArrays 字节数组列表
	 * @return 合并后的字节数组
	 */
	public static byte[] merge(byte[]... bytesArrays) {
		if (bytesArrays == null) {
			return Bytes.EMPTY_ARRAY;
		}
		if (bytesArrays.length == 0) {
			return Bytes.EMPTY_ARRAY;
		}

		int totalLength = 0;
		for (byte[] array : bytesArrays) {
			totalLength = totalLength + array.length;
		}
		if (totalLength == 0) {
			return Bytes.EMPTY_ARRAY;
		}

		byte[] joinArray = new byte[totalLength];
		int offset = 0;
		for (byte[] array : bytesArrays) {
			System.arraycopy(array, 0, joinArray, offset, array.length);
			offset = offset + array.length;
		}
		return joinArray;
	}

	/**
	 * 判断给定字节数组是否未空(即为 null 或者长度为 0).
	 * @param bytes 给定字节数组
	 * @return true 为空, false 不为空
	 */
	public static boolean isEmpty(byte[] bytes) {
		return bytes == null || bytes.length == 0;
	}

	/**
	 * 判断给定字符数组是否未空(即为 null 或者长度为 0).
	 * @param chars 给定字符数组
	 * @return true 为空, false 不为空
	 */
	public static boolean isEmpty(char[] chars) {
		return chars == null || chars.length == 0;
	}

	/**
	 * 判断给定字节数组不为空, 即长度大于 0.
	 * @param bytes 给定字节数组
	 * @return true 不为空, false 为空
	 */
	public static boolean isNotEmpty(byte[] bytes) {
		return bytes != null && bytes.length > 0;
	}

}
