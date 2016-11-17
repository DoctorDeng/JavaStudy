package thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * 练习 Java 阻塞队列  
 */
public class TestBlockingQueue {
	
	public static void testBlockQueue() {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
		for (int i = 0; i < 2; i++) {
			new Thread(()->{
				while (true) {
					try {
						Thread.sleep((long)(Math.random() * 10000));
						System.out.println(Thread.currentThread().getName()+
								"正准备放数据!");
						queue.put("邓华杰" + (new Random()).nextInt());
						
						System.out.println(Thread.currentThread().getName() + 
								"已经放了" + queue.size() + "个数据");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(10000);
					System.out.println(Thread.currentThread().getName() + 
							"正在取数据");
					String name = queue.take();
					
					System.out.println(Thread.currentThread().getName() + 
							"取出的数据为: " + name + " 目前队列有" + queue.size() +
							"个数据");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	public static void main(String[] args) {
		testBlockQueue();
	}
}
