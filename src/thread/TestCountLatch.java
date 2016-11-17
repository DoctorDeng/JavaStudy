package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 练习 CountDownLatch
 * 
 * @author Doctor邓
 *
 */
public class TestCountLatch {

	public static void testCountLatch() {
		ExecutorService service = Executors.newCachedThreadPool();

		CountDownLatch order = new CountDownLatch(1);
		CountDownLatch answer = new CountDownLatch(3);

		for (int i = 0; i < 3; i++) {
			Runnable run = () -> {
				try {
					System.out.println("线程" + Thread.currentThread().getName() + "正准备接受命令");
					order.await();

					System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");

					Thread.sleep((long) (Math.random() * 10000));

					System.out.println("线程" + Thread.currentThread().getName() + "回应命令结束");

					answer.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
			service.execute(run);
		}

		try {
			Thread.sleep((long) (Math.random() * 10000));

			System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");

			order.countDown();

			System.out.println("线程" + Thread.currentThread().getName() + "已发送命令, 正在等待结果");

			answer.await();

			System.out.println("线程" + Thread.currentThread().getName() + "已收到所有相应结果");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		service.shutdown();

	}

	public static void main(String[] args) {
		testCountLatch();
	}
}
