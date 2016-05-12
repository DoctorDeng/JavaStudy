package practice.thread;
/*
 * 这是隋唐演义的大戏舞台
 */
public class Stage extends Thread{

	public void run() {
		
		System.out.println("欢迎观看隋唐演义！");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		
		System.out.println("大幕徐徐拉开!");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		System.out.println("话说隋朝末年，隋军与农民起义军杀的昏天黑地。。。。");
		
		ArmyRunnable armyCountry = new ArmyRunnable();
		ArmyRunnable armyFarmer = new ArmyRunnable();
		
		Thread armyOfCountry = new Thread(armyCountry, "隋军");
		Thread armyOfFarmer = new Thread(armyCountry, "农民军");
		
		//启动线程，让军队开始作战
		armyOfCountry.start();
		armyOfFarmer.start();
		
		//舞台线程休眠，大家专心观看军队的厮杀(即运行armyOfCountry和armyOfFarmer两个线程)
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		System.out.println("正当双方激战正酣，半路杀出了个程咬金");
		Thread mrCheng = new KeyPersonThread();
		mrCheng.setName("程咬金");
		System.out.println("程咬金的理想就是结束战争，是百姓安居乐业!");
		
//		armyCountry.keepRunning = false;  //正确的关闭
//		armyFarmer.keepRunning = false;
		armyOfCountry.stop();   //不正确的关闭
		armyOfFarmer.stop();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		mrCheng.start();
		
		//让所有线程等待mrCheng执行完成后再开始执行
		try {
			mrCheng.join();
		} catch (InterruptedException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		System.out.println("战争结束，人民安居乐业，程咬金实现了他积极的人生梦想，为人民做出了贡献");
		System.out.println("谢谢观看隋唐演义，再见！");
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Stage().start();
	}

}
