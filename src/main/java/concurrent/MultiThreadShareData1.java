package concurrent;

/**
 * 练习多个线程共享数据
 *
 * @author Doctor邓
 */
public class MultiThreadShareData1 {

    public static void main(String[] args) {
        ShareDataOne sharaData = new ShareDataOne();
		/*	
		new Thread(() -> {
			sharaData.decrement();
			sharaData.print();
		}).start();
		
		new Thread(() -> {
			sharaData.increment();
			sharaData.print();
		}).start();*/

        new Thread(new MyRunnableDecrement(sharaData)).start();
        new Thread(new MyRunnableIncrement(sharaData)).start();

    }
}

class MyRunnableDecrement implements Runnable {
    private ShareDataOne shareData;

    public MyRunnableDecrement(ShareDataOne shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        shareData.decrement();
    }
}

class MyRunnableIncrement implements Runnable {
    private ShareDataOne shareData;

    public MyRunnableIncrement(ShareDataOne shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        shareData.increment();
    }
}

class ShareDataOne {
    private int j = 100;

    public synchronized void increment() {
        j++;
        print();
    }

    public synchronized void decrement() {
        j--;
        print();
    }

    public void print() {
        System.out.println(Thread.currentThread().getName() + "操作 J 后, J 的值是: " + j);
    }
}