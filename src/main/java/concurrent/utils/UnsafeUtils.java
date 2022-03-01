package concurrent.utils;

/**
 * Unsafe 工具类.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2022/3/1 18:15
 */
public class UnsafeUtils {

	/**
	 * 获取 {@link sun.misc.Unsafe}.
	 * @return {@link sun.misc.Unsafe}
	 */
	public static sun.misc.Unsafe getUnsafe() {
		try {
			return sun.misc.Unsafe.getUnsafe();
		} catch (SecurityException ignored) {
			// ignore.
		}
		try {
			return java.security.AccessController.doPrivileged(
					new java.security.PrivilegedExceptionAction<sun.misc.Unsafe>() {
						@Override
						public sun.misc.Unsafe run() throws Exception {
							Class<sun.misc.Unsafe> k = sun.misc.Unsafe.class;
							for (java.lang.reflect.Field f : k.getDeclaredFields()) {
								f.setAccessible(true);
								Object x = f.get(null);
								if (k.isInstance(x)) return k.cast(x);
							}
							throw new NoSuchFieldError("the Unsafe");
						}
					});
		} catch (java.security.PrivilegedActionException e) {
			throw new RuntimeException("Could not initialize intrinsics", e.getCause());
		}
	}
}
