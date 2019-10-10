package string;

import java.text.MessageFormat;

public class StringFormatTest {
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
	 * 性能优于 {@link StringFormatTest#stringFormat(String, Object...)}
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
