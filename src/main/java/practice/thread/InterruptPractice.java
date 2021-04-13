package practice.thread;
/*
 * 练习Intrrupt的使用
 */

public class InterruptPractice extends Thread {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		InterruptPractice thread = new InterruptPractice();
		System.out.println("Starting thread...");
		thread.start();
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Interrupting thread...");
		thread.interrupt();
		
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Stopping application...");
		
	}
	
	public void run() {
//		while (true) {
//			System.out.println("Thread is running...");
//			long time = System.currentTimeMillis();
//			while ((System.currentTimeMillis() - time < 1000)) {
//				//减少屏幕输出的空循化
//			}
//		}
		
		
		//退出旗标的方法退出线程
		while (!this.isInterrupted()) {
		System.out.println("Thread is running...");
//		long time = System.currentTimeMillis();
//		while ((System.currentTimeMillis() - time < 1000)) {
//			//减少屏幕输出的空循化
//		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}	
	}
}
