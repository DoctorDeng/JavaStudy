package corejava;

/**
 * 练习 Java 中循环的跳出
 * 
 * @author doctordeng
 *
 */
public class TestLoop {

	public static void testSkipLoop() {
		ok: for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.println("i=" + i + ",j=" + j);
				if (i == 5)
					break ok;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSkipLoop();
	}

}
