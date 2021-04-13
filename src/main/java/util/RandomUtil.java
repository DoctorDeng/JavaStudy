package util;

public class RandomUtil {
	/**
	 * 
	 * @param start 0~127
	 * @param end   0~127
	 * @return char
	 */
	public static char randomASCIIChar(int start, int end) {
		if (start < 0 || start > 127) {
			throw new IndexOutOfBoundsException("randomAsciiChar index out of range(0~127), start:" + start);
		}
		if (end < 0 || end > 127) {
			throw new IndexOutOfBoundsException("randomAsciiChar index out of range(0~127), end:" + end);
		}
		if (start > end) {
			throw new IndexOutOfBoundsException("randomAsciiChar index out of range(0~127), length:" + (end - start));
		}
		if (start == end) {
			return (char) start;
		}
		
		int randomInt = (int) (Math.random() * (end - start + 1) + start);
		return (char) randomInt;
	}
	public static char randomChar() {
		if (Math.random() > 0.5) {
			return randomLowerChar();
		} 
		return randomUpperChar();
	}
	public static char randomLowerChar() {
		// ASCII：97 ~ 122 大写字母
		return randomASCIIChar(97, 122);
	}
	public static char randomUpperChar() {
		// ASCII：65 ~ 90 小写字母
		return randomASCIIChar(65, 90);
	}
	public static char randomNumber() {
		// ASCII：48~57 数字
		return randomASCIIChar(48, 57);
	}
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			System.out.println("数字:" + randomChar());
		}
	}
}
