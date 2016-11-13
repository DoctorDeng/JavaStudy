package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 练习 Semaphore 
 * @author Doctor邓
 *
 */
public class TestSemaphore {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		//Semaphore 一个技术信号量
		final Semaphore sp = new Semaphore(3);
		
		for (int i = 0; i < 10; i++) {
			Runnable runnable = () -> {
				try {
					//从信号量获取一个许可, 在提供一个许可之前将线程阻塞, 否则线程被中断
					sp.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("线程" + Thread.currentThread().getName() 
						+ "进入, 当前已有" + (3 - sp.availablePermits()) + "线程进入" );
			
				try {
					Thread.sleep((long)(Math.random() * 10000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("线程" + Thread.currentThread().getName() + 
						"即将离开");
				//释放一个许可
				sp.release();
			};
			
			service.execute(runnable);
		}
		service.shutdown();
	}

}
