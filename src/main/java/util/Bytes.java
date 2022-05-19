package util;

import common.Globals;

import java.nio.charset.Charset;

/**
 * 字节工具类.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2022/1/11 18:59
 */
public abstract class Bytes {

	private Bytes() {

	}

	/**
	 * 长度为 0 的字节数组常量.
	 */
	public static final byte[] EMPTY_ARRAY = new byte[0];

	/**
	 * 基于字典顺序比较两个字节数组大小. 更好的排序方式可以查看 Guava 库中的:
	 * <ul>
	 * <li>UnsignedBytes.lexicographicalComparator().</li>
	 * <li>SignedBytes.lexicographicalComparator().</li>
	 * </ul>
	 * @param left 待比较字节数组
	 * @param right 待比较字节数组
	 * @return 比较结果
	 */
	public static int compare(byte[] left, byte[] right) {
		int length = Math.min(left.length, right.length);
		for (int i = 0; i < length; i++) {
			int a = (left[i] & 0xff);
			int b = (right[i] & 0xff);
			if (a != b) {
				return a - b;
			}
		}
		return left.length - right.length;
	}

	/**
	 * 将字符串按照默认的编码转换为字节数组.
	 *
	 * @param data 给定字符串
	 * @return 转换后的字节数组
	 */
	public static byte[] toBytes(String data) {
		if (StringUtils.isEmpty(data)) {
			return Bytes.EMPTY_ARRAY;
		} else {
			return data.getBytes(Globals.getCharset(null));
		}
	}

	/**
	 * 将给定字符串按照指定编码转换为字节数组.
	 * @param data 给定字符串
	 * @param charset 字符串编码
	 * @return 转换后的字节数组
	 */
	public static byte[] toBytes(String data, Charset charset) {
		if (StringUtils.isEmpty(data)) {
			return Bytes.EMPTY_ARRAY;
		} else {
			return data.getBytes(Globals.getCharset(charset));
		}
	}

}
