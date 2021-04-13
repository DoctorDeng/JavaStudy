package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 练习 Java 线程池
 * 
 * @author Doctor邓
 *
 */
public class ThreadPoolTest {
	//测试固定线程池
	public static void testFixedThreadPool() {
		// 创建线程池, 这里创建线程数量固定的 固定线程池
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 5; i++) {
			final int task = i;
			// 向线程池中加入任务
			threadPool.execute(() -> {
				for (int j = 0; j < 5; j++) {
					System.out.println(Thread.currentThread().getName() + "第" + (j + 1) + "次执行,在任务" + task + "中");
				}
			});
		}
		System.out.println("5 个任务都被提交了");
		// 在所有的任务都执行完成后, 关闭线程池, 否则因为线程池中一直由线程存在, 程序将会一直运行
		threadPool.shutdown();
	}
	
	//测试缓冲线程池, 即线程池中线程数量不固定, 可随任务多少而增加或减少
	public static void testCachedTreadPool() {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			final int task = i;
			threadPool.execute(() -> {
				for (int j = 0; j < 5; j++) {
					System.out.println(Thread.currentThread().getName() + "第" + (j + 1) + "次执行,在任务" + task + "中");
				}
			});
		}
		threadPool.shutdown();
	}
	//测试单一线程池, 即只有一个线程的线程池, (当线程池中的线程死掉后, 线程池会再生成一个线程来继续服务)
	public static void testSingleThreadPool() {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			final int task = i;
			threadPool.execute(() -> {
				for (int j = 0; j < 5; j++) {
					System.out.println(Thread.currentThread().getName() + "第" + (j + 1) + "次执行,在任务" + task + "中");
				}
			});
		}
		threadPool.shutdown();
	}
	//测试用线程池启动定时器
	public static void testScheduledThreadPool () {
		//创建一个用于定时器执行的任务
		Runnable command = () -> System.out.println("执行");
		/** 
		 * 通过线程池启动定时器, 这里为 10 秒后启动任务
		 * @param command 定时启动的任务
		 * @param 10                延迟执行时间
		 * @param command, 6, 2, TimeUnit.MILLISECONDS 设置时间单位为秒
		 */
		Executors.newScheduledThreadPool(3).
				schedule(command, 10, TimeUnit.SECONDS);
		/**
		 * 6 秒后开始执行, 以后 每隔 2 秒执行一次
		 * scheduleAtFixedRate 有一个确定就是无法在指定时间来来执行任务, 例如：指定16年10月10日12:00 执行
		 * 实现指定时间执行任务的思路:
		 *     将指定执行任务的时间与当前时间相减获取时间差, 然后将延迟启动时间设置为 时间差
		 */
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(command, 6, 2, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) {
		//testFixedThreadPool();
		//testCachedTreadPool();
		//testSingleThreadPool();
		testScheduledThreadPool();
	}

}
