package common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 存放全局常量或通用方法.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/12/30 11:10
 */
public abstract class Globals {

	private Globals() {
	}

	/**
	 * 全局字符编码: UTF-8.
	 */
	public static final Charset CHARSET = StandardCharsets.UTF_8;

	public static Charset getCharset(Charset charset) {
		return charset != null ? charset : CHARSET;
	}

}
