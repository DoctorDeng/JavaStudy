package string;

import java.text.MessageFormat;


public class StringFormatTest {
	/**
	 * 格式化字符串
	 * @param template 需要格式字话的符串, 格式见 {@link java.util.Formatter}
	 * @param params 格式化字符串需要的参数, 格式见 {@link java.util.Formatter}
	 * @see java.lang.String#format(String, Object...)
	 * @return 格式化后的字符串
	 */
	public static String stringFormat(String template, Object... params) {
		if (template == null) {
			return null;
		} else if (params == null) {
			return template;
		} else {
			return String.format(template, params);
		}
	}
	/**
	 * 格式化字符串, 性能优于 {@link StringFormatTest#stringFormat(String, Object...)}
	 * @param template 需要格式字话的符串
	 * @param params   格式化字符串需要的参数
	 * @see java.text.MessageFormat#format(String, Object...)
	 * @return 格式化后的字符串
	 */
	public static String messageFormat(String template, Object... params) {
		if (template == null) {
			return null;
		} else if (params == null) {
			return template;
		} else {
			return MessageFormat.format(template, params);
		}
	}
	
	
	private static void performanceTesting() {
		Object[] params = new Object[] {"World", "World2"};
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			stringFormat("Hello %s, Hello %s", params);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("耗时:" + (endTime - startTime));
		startTime = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			messageFormat("Hello {0}, Hello {1}", params);
		}
		endTime = System.currentTimeMillis();
		System.out.println("耗时:" + (endTime - startTime));
	}
	public static void main(String[] args) {
		performanceTesting();
	}
}
