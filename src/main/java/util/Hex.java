package util;


import common.Globals;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 16 进制(Hex) 工具类.
 * <p>
 * 十六进制(简写为 hex 或下标 16)在数学中是一种逢 16 进 1 的进位制, 一般用数字 0 到 9 和字母 A 到 F 表示 (其中: A~F 即 10~15).
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2022/4/12 15:54
 */
public abstract class Hex {

	private Hex() {
	}

	/**
	 * 用于编码的字符数组.
	 */
	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };
	/**
	 * 用于编码的字符数组.
	 */
	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	/**
	 * 用于编码的字节数组.
	 */
	private static final byte[] BYTE_DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	/**
	 * 用于编码的字节数组.
	 */
	private static final byte[] BYTE_DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	/*
	 * 用于解码的字节数组.
	 */
	private static final byte[] BYTE_DECODING_TABLE = new byte[128];

	static {
		Arrays.fill(BYTE_DECODING_TABLE, (byte) 0xff);
		for (int i = 0; i < BYTE_DIGITS_LOWER.length; i++) {
			BYTE_DECODING_TABLE[BYTE_DIGITS_LOWER[i]] = (byte)i;
		}
		BYTE_DECODING_TABLE['A'] = BYTE_DECODING_TABLE['a'];
		BYTE_DECODING_TABLE['B'] = BYTE_DECODING_TABLE['b'];
		BYTE_DECODING_TABLE['C'] = BYTE_DECODING_TABLE['c'];
		BYTE_DECODING_TABLE['D'] = BYTE_DECODING_TABLE['d'];
		BYTE_DECODING_TABLE['E'] = BYTE_DECODING_TABLE['e'];
		BYTE_DECODING_TABLE['F'] = BYTE_DECODING_TABLE['f'];
	}

	// ****************************************************************************************
	// *  char[] version
	// ****************************************************************************************

	/**
	 * 将字节数组编码为 16 进制的字符数组.
	 * <p>
	 * 注: 一个字节(byte) 8 bit, 一个字符(char) 16 bit, 因此转换后字节数组变成字符数组.
	 *
	 * @param data 待编码的字节数组
	 * @return 16 进制的字符数组
	 */
	public static char[] encode(byte[] data) {
		return encode(data, true);
	}

	/**
	 * 将字节数组编码为 16 进制的字符数组.
	 * <p>
	 * 注: 一个字节(byte) 8 bit, 一个字符(char) 16 bit, 因此转换后字节数组变成字符数组.
	 *
	 * @param data 待编码的字节数组
	 * @param toLowerCase true 转换为小写， false 转换为大写
	 * @return 16 进制的字符数组
	 */
	public static char[] encode(byte[] data, boolean toLowerCase) {
		if (ArrayUtils.isEmpty(data)) {
			return Chars.EMPTY_ARRAY;
		} else {
			return encode(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
		}
	}

	/**
	 * 将字符串编码为 16 进制的字符数组.
	 * @param str 待编码的字符串
	 * @param charset 字符串编码, 可以为空
	 * @return 16 进制的字符数组
	 */
	public static char[] encode(String str, Charset charset) {
		if (StringUtils.isEmpty(str)) {
			return Chars.EMPTY_ARRAY;
		} else {
			return encode(str.getBytes(Globals.getCharset(charset)));
		}
	}

	/**
	 * 将字符串编码为 16 进制的字符数组.
	 * @param str 待编码的字符串
	 * @return 16 进制的字符数组
	 */
	public static char[] encode(String str) {
		return encode(str, null);
	}

	/**
	 * 将字符串编码为 16 进行表示的字符串.
	 * @param str 待编码的字符串
	 * @return 16 进制表示的字符串
	 */
	public static String encodeString(String str) {
		return new String(encode(str));
	}

	/**
	 * 将字节数组编码为 16 进制的字符串.
	 * @param data 待编码的字节数组
	 * @return 16 进制表示的字符串
	 */
	public static String encodeString(byte[] data) {
		return new String(encode(data));
	}

	private static char[] encode(byte[] data, char[] digits) {
		final int len = data.length;
		final char[] out = new char[len << 1];
		// two characters from the hex value.
		for (int i = 0, j = 0; i < len; i++) {
			out[j++] = digits[(0xF0 & data[i]) >>> 4]; // 高位.
			out[j++] = digits[0x0F & data[i]];         // 低位.
		}
		return out;
	}

	/**
	 * 将 16 进制编码的字符数组解码为二进制的字节数组.
	 * @param encoded 16 进制编码的字符数组
	 * @return 二进制表示的字节数组
	 */
	public static byte[] decode(char[] encoded) {
		if (ArrayUtils.isEmpty(encoded)) {
			return Bytes.EMPTY_ARRAY;
		}
		int len = encoded.length;
		byte[] out = new byte[len >> 1];
		decode(encoded, out);
		return out;
	}

	/**
	 * 将 16 进制编码的字符序列解码为二进制的字节数组.
	 * @param encoded 16 进制编码的字符序列
	 * @return 二进制表示的字节数组
	 */
	public static byte[] decode(CharSequence encoded) {
		if (StringUtils.isEmpty(encoded)) {
			return Bytes.EMPTY_ARRAY;
		}
		int len = encoded.length();
		if ((len & 0x01) != 0) {
			throw new IllegalArgumentException("Odd number of characters.");
		}
		final byte[] out = new byte[len >> 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(encoded.charAt(j), j) << 4;
			j++;
			f = f | toDigit(encoded.charAt(j), j);
			j++;
			out[i] = (byte) (f & 0xFF);
		}
		return out;
	}

	/**
	 * 将 16 进制表示的字符串解码而二进制表示的字符串
	 * @param str 16 进制表示的字符串
	 * @param charset 字符串编码
	 * @return 二进制表示的字符串
	 */
	public static String decodeString(String str, Charset charset) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		return new String(decode(str), charset);
	}

	public static String decodeString(String str) {
		return decodeString(str, Globals.CHARSET);
	}

	/**
	 * 将表示十六进制值的字符数解码为具有相同值的字节数组.
	 * 返回的数组将是传递数组长度的一半，因为它需要两个字符来表示任何给定的字节.
	 * 如果传递的 char 数组具有奇数个元素，则会引发异常
	 * @param data 16 进制表示的字符数组
	 * @param out 一个字节数组，包含从提供的 char 数组解码的二进制数据
	 */
	private static void decode(final char[] data, final byte[] out) {
		final int len = data.length;
		if ((len & 0x01) != 0) {
			throw new IllegalArgumentException("Odd number of characters.");
		}
		// two characters form the hex value.
		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(data[j], j) << 4;
			j++;
			f = f | toDigit(data[j], j);
			j++;
			out[i] = (byte) (f & 0xFF);
		}
	}

	/**
	 * 将十六进制字符转换成一个整数.
	 *
	 * @param chr    十六进制字符
	 * @param index  十六进制字符在字符数组中的位置
	 * @return 一个整数
	 */
	private static int toDigit(char chr, int index) {
		int digit = Character.digit(chr, 16);
		if (digit < 0) {
			throw new RuntimeException("Illegal hexadecimal character " + chr + " at index " + index);
		}
		return digit;
	}

	// ****************************************************************************************
	// *  byte[] version
	// ****************************************************************************************

	/**
	 * 将字节数组编码为 16 进制的字节数组.
	 * <p>
	 * 注: 一个字节(byte) 8 bit, 转换后容量翻倍.
	 *
	 * @param data 待编码的字节数组
	 * @return 16 进制的字节数组
	 */
	public static byte[] encodeAsBytes(byte[] data) {
		return encodeAsBytes(data, true);
	}

	/**
	 * 将字节数组编码为 16 进制的字节数组.
	 * <p>
	 * 注: 一个字节(byte) 8 bit, 转换后容量翻倍.
	 *
	 * @param data 待编码的字节数组
	 * @param toLowerCase true 转换为小写， false 转换为大写
	 * @return 16 进制的字节数组
	 */
	public static byte[] encodeAsBytes(byte[] data, boolean toLowerCase) {
		if (ArrayUtils.isEmpty(data)) {
			return Bytes.EMPTY_ARRAY;
		} else {
			return encode(data, toLowerCase ? BYTE_DIGITS_LOWER : BYTE_DIGITS_UPPER);
		}
	}

	private static byte[] encode(byte[] data, byte[] digits) {
		final int len = data.length;
		final byte[] out = new byte[len << 1];
		// two characters from the hex value.
		for (int i = 0, j = 0; i < len; i++) {
			out[j++] = digits[(0xF0 & data[i]) >>> 4]; // 高位.
			out[j++] = digits[0x0F & data[i]];         // 低位.
		}
		return out;
	}

	/**
	 * 将表示十六进制值的字节数组解码为具有相同值的字节数组.
	 * 返回的数组将是传递数组长度的一半.
	 * @param data 16 进制表示的字节数组
	 */
	public static byte[] decode(byte[] data) {
		if (ArrayUtils.isEmpty(data)) {
			return Bytes.EMPTY_ARRAY;
		}
		final int inLen = data.length;
		if ((inLen & 0x01) != 0) {
			throw new IllegalArgumentException("Odd number of data.");
		}
		final byte[] out = new byte[data.length >> 1];
		byte b1, b2;
		int i = 0;
		int j = 0;
		while (i < inLen) {
			b1 = BYTE_DECODING_TABLE[data[i++]];
			b2 = BYTE_DECODING_TABLE[data[i++]];
			if ((b1 | b2) < 0) {
				throw new IllegalArgumentException("invalid characters encountered in Hex data");
			}
			out[j++] = (byte) ((b1 << 4) | b2);
		}
		return out;
	}

}
