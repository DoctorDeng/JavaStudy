package thread;

public class MultiThreadShareData3 {

	private int j = 100;
	public synchronized void increment() {
		j++;
		print();
	}
	public synchronized void decrement() {
		j--;
		print();
	}
	public void print() {System.out.println(Thread.currentThread().getName() + "操作 J 后, J 的值是: " + j);}
	
	public static void main(String[] args) {
		MultiThreadShareData3 thread3 = new MultiThreadShareData3();
		new Thread(thread3.new MyThreadDecrement()).start();
		new Thread(thread3.new MyThreadIncrement()).start();
	}
	class MyThreadIncrement implements Runnable {
		@Override
		public void run() {
			increment();
		}
	}
	class MyThreadDecrement implements Runnable {
		@Override
		public void run() {
			decrement();
		}
	}	
}
