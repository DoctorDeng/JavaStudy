package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用阻塞队列实现两个线程之间的同步通信
 * @author Doctor邓
 *
 */
public class TestThreadCommunicationByBlockingQueue {
	private static BlockingQueue<String> queueA  = new ArrayBlockingQueue<>(1);
	private static BlockingQueue<String> queueB = new ArrayBlockingQueue<>(1);
	
	public static void testCommunication() {
		Thread  a = new Thread(() ->{
			int i = 0;
			try {
				while(true) {
					System.out.println(Thread.currentThread().getName() + 
							"正在放数据, 放入的数据为: " + "数据A" + i);
					queueA.put("数据A" + i);
					i++;
					System.out.println(Thread.currentThread().getName() + 
							"正在取数据, 取出的数据为" + queueB.take());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread b = new Thread(()->{
			int i = 0;
			try {
				while(true) {
					System.out.println(Thread.currentThread().getName() + 
							"正在取数据, 取出的数据为" + queueA.take());
					System.out.println(Thread.currentThread().getName() + 
							"正在放数据, 放入的数据为: " + "数据B" + i);
					queueB.put("数据B" + i);
					i++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		a.start();
		b.start();
	}
	public static void main(String[] args) {
		testCommunication();
	}
}