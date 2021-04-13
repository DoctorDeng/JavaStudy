package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 模仿 ThreadLocal 达到同一个线程共享变量
 * @author Doctor邓
 */
public class ThreadLocalSimulation {
	private static Map<Thread, Integer> threadData = new HashMap<>();
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int data = new Random().nextInt();
				threadData.put(Thread.currentThread(), data);
				System.out.println(Thread.currentThread().getName() + "放入数据：" + data);
				new A().getData();
				new B().getData();
			}
		}).start();

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int data = new Random().nextInt();
				threadData.put(Thread.currentThread(), data);
				System.out.println(Thread.currentThread().getName() + "放入数据：" + data);
				new A().getData();
				new B().getData();
			}
		}).start();
	}

	static class A{
		public int getData(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("A 从" + Thread.currentThread().getName() + "线程，取出数据：" + data);
			return data;
		}
	}
	
	static class B{
		public int getData(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("B 从" + Thread.currentThread().getName() + "线程，取出数据：" + data);
			return data;
		}
	}
}
