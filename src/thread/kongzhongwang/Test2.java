package thread.kongzhongwang;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class Test2 {
	
		public static void main(String[] args) {
			ExecutorService service = Executors.newFixedThreadPool(10);
			//BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
			SynchronousQueue<String> queue = new SynchronousQueue<>();
			Semaphore sp = new Semaphore(1);
			
			System.out.println("begin:"+(System.currentTimeMillis()/1000));
			for(int i=0;i<10;i++){  //这行不能改动
				service.execute(()->{
					try {
						sp.acquire();
						String output = TestDo.doSome(queue.take());
						System.out.println(Thread.currentThread().getName()+ ":" + output);
						sp.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
				String input = i+"";  //这行不能改动
				try {
					queue.put(input);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	//不能改动此TestDo类
	class TestDo {
		public static String doSome(String input){
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String output = input + ":"+ (System.currentTimeMillis() / 1000);
			return output;
		}
	}