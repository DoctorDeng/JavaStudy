package datastructureAndalgorithm.practice.offer;

/**
 * 剑指 Offer 58 - II. 左旋转字符串.
 * <pre>
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能.
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab".
 *
 * 示例 1：
 *     输入: s = "abcdefg", k = 2
 *     输出: "cdefgab"
 *
 * 示例 2：
 *     输入: s = "lrloseumgh", k = 6
 *     输出: "umghlrlose"
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof">剑指 Offer 58 - II. 左旋转字符串</a>
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/7 18:54
 */
public class Offer58 {

	public String reverseLeftWords(String s, int n) {
		if (s == null) {
			return null;
		}
		int length = s.length();
		if (length <= n) {
			n = s.length();
		}
		int offset = length - n;
		char[] chars = new char[length];
		for (int i = 0; i < length; i++) {
			char chr = s.charAt(i);
			if (i < n) {
				chars[offset++] = chr;
			}
			else {
				chars[i - n] = chr;
			}
		}
		return new String(chars);
	}

	public static String reverseLeftWordsWithArrayCopy(String s, int n) {
		if (s == null) {
			return null;
		}
		int length = s.length();
		if (length == n) {
			return s;
		}
		else if (length < n) {
			n = s.length();
		}
		char[] oldChars = s.toCharArray();
		char[] chars = new char[length];
		System.arraycopy(oldChars, 0, chars, length - n, n);
		System.arraycopy(oldChars, n, chars, 0, length - n);
		return new String(chars);
	}

	public static void main(String[] args) {
		System.out.println(reverseLeftWordsWithArrayCopy("lrloseumgh", 6));
	}

}
