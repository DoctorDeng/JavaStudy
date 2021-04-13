package practice.thread;
/*
 * 隋唐演义之军队类:模拟双方的行为
 */

public class ArmyRunnable implements Runnable{
	
	//volatile保证了线程可以正确的读取其他线程写入的值。
	volatile boolean keepRunning = true;
	
	public void run() {
		while (keepRunning) {
			//发动连击
			for (int i=0; i<5; i++) {
				System.out.println(Thread.currentThread().getName() +
						"进攻双方[" + i + "]");
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				//让出处理器资源，让处理器重新调度，这样，下次线程执行的先后顺序就是随机的了
				Thread.yield();
			}
		}
		System.out.println(Thread.currentThread().getName() + "结束了战斗!");
	}
}
