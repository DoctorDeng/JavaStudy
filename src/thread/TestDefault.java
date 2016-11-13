package thread;

public interface TestDefault {
	default void test() {
		System.out.println("dsjdl k");
	}
	
	void out();
}
class TestA {
	public static void main(String[] args) {
		TestDefault test = () -> {
			System.out.println( 1 + 1);
		};
		
		test.out();
		test.test();
	}
}