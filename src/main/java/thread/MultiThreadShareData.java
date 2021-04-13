package thread;
/**
 * 练习多个线程共享数据
 * @author Doctor邓
 *
 */
public class MultiThreadShareData {
	public static void main(String[] args) {
		//通过此 Runnable 对象来实现对数据的操作
		ShareData shareData = new ShareData();
		new Thread(shareData).start();
		new Thread(shareData){}.start();
	}
}
class ShareData implements Runnable{
	int j = 100;
	public synchronized void increment() {
		j++;
	}
	public synchronized void decrement() {
		j--;
	}
	@Override
	public void run() {
		decrement();
	}
}