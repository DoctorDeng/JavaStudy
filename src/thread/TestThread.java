package thread;

public class TestThread {

	public static void main(String[] args) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
					//不推荐，当 Thread 采用 new Thread(Runnable) 方式初始化时，
					//this 就是指 Runnable 即线程要执行的代码，所以会出错
					System.out.println(this.getName());
				}
			}
		};
		thread.start();
		
		Thread thread2 = new Thread(new Runnable(){
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			}
		});
		thread2.start();
		//以下代码结果以 子类的 run() 方法为准，子类覆盖了父类的run() 方法，所以不会执行父类的的 Run() 方法
		//其中new Thread(){ run() } 相当于创建了一个 Thread() 的子类，而 new Thread( Runnable) 则是
		//给 Thread 对象初始化赋值
		new Thread(new Runnable(){
			public void run(){
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Runnable:" + Thread.currentThread().getName());
				}
			}
		}){
			public void run(){
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
					//不推荐，当 Thread 采用 new Thread(Runnable) 方式初始化时，
					//this 就是指 Runnable 即线程要执行的代码，所以会出错
					System.out.println(this.getName());
				}
			}
		}.start();
	}

}
