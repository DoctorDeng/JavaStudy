package practice.thread;

public class TestSystem {

	public static void main(String[] args) {
		Thread actor = new Actor();
		Thread actress = new Thread(new Actress(), "Ms.Runnable");
		actor.setName("Mr.Thread");
		
		actor.run();
		actress.start();
	}
}
