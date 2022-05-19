package util;

/**
 * 字符串工具类.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2022/4/12 13:55
 */
public abstract class StringUtils {

	private StringUtils() {
	}

	/**
	 * 判断给定字符序列是否为空.
	 * @param str 给定字符序列
	 * @return true 为空, false 不为空
	 */
	public static boolean isEmpty(CharSequence str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 判断给定字符序列是否不为空.
	 * @param str 给定字符序列
	 * @return true 不为空, false 为空
	 */
	public static boolean isNotEmpty(CharSequence str) {
		return str != null && str.length() > 0;
	}

}
