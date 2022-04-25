package jvm.gc;

/**
 * 大对象直接进入老年代.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2022/4/25 19:38
 */
public class BigObject {
	private static final int _1MB = 1024 * 1024;

	/**
	 * VM 参数：
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:PretenureSizeThreshold=3145728
	 */
	public static void testPretenureSizeThreshold() {
		// 直接分配在老年代中.
		byte[] allocation;
		allocation = new byte[4 * _1MB];
	}

	public static void main(String[] args) {
		testPretenureSizeThreshold();
	}

}
