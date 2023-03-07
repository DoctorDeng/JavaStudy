package datastructureAndalgorithm.practice.offer;

/**
 * 剑指 Offer 05. 替换空格.
 * <pre>
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20".
 * 示例 1：
 *     输入：s = "We are happy."
 *     输出："We%20are%20happy."
 * </pre>
 * <p>
 * 说明见 <a href="https://leetcode.cn/problems/ti-huan-kong-ge-lcof">剑指 Offer 05. 替换空格</a>
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2023/3/7 18:39
 */
public class Offer05 {

	public String replaceSpace(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		char[] chars = s.toCharArray();
		for (char chr: chars) {
			if (chr == ' ') {
				sb.append("%20");
			}
			else {
				sb.append(chr);
			}
		}
		return sb.toString();
	}

	public String replaceSpaceWithArray(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		int length = s.length();
		char[] newChars = new char[length * 3];
		int newLength = 0;
		for (int i = 0; i < length; i++) {
			char chr = s.charAt(i);
			if (chr == ' ') {
				newChars[newLength++] = '%';
				newChars[newLength++] = '2';
				newChars[newLength++] = '0';
			}
			else {
				newChars[newLength++] = chr;
			}
		}
		return new String(newChars, 0, newLength);
	}

}
