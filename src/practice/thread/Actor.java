package practice.thread;
/*
 * 隋唐演义之演员类
 */
public class Actor extends Thread {
	
	public void run() {
		System.out.println(getName() + "是一个演员!");
		int count =0;
		boolean keepRunning = true;
		while(keepRunning) {
			System.out.println(getName() + "登台演出:" + (++count));
			if (count == 20) {
				keepRunning = false;
			}
			
			if (count%1 == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
		
		System.out.println(getName() + "的演出结束了!");
	}
}
class Actress implements Runnable {
	public void run() {
		System.out.println(Thread.currentThread().getName() + "是一个演员!");
		int count =0;
		boolean keepRunning = true;
		while(keepRunning) {
			System.out.println(Thread.currentThread().getName() + "登台演出:" + (++count));
			if (count == 30) {
				keepRunning = false;
			}
			
			if (count%1 == 0) {
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
		
		System.out.println(Thread.currentThread().getName() + "的演出结束了!");
	}
}
