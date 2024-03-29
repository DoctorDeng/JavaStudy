package practice.thread;

public class EnergySystem {

	//能量盒子,能量存储的地方
	private final double[] energyBoxes;
	private final Object lockObj = new Object();     //多线程的锁
	
	/**
	 * 
	 * @param n 能量盒子的数量
	 * @param initialEnergy 每个能量盒子初始含有的能量值
	 */
	public EnergySystem(int n, double initialEnergy) {
		 energyBoxes = new double[n];
		 
		 for (int i=0; i<energyBoxes.length; i++) {
			 energyBoxes[i] = initialEnergy;
		 }
	}
	
	/**
	 * 能量的转移，从一个盒子到另一个盒子
	 * @param from   能量源
	 * @param to     能量终点
	 * @param amount 能量值
	 */
	public void transfer (int from, int to, double amount) {
		synchronized(lockObj) {
//			if (energyBoxes[from] < amount) {
//				return;
//			} 
			
			//while循环，保证条件不满足时任务都会被条件阻挡
			//而不是继续竞争CPU资源
			while (energyBoxes[from] < amount) {
				try {
					//条件不满足时，将当前线程放入Wait Set中，Wait Set是属于锁对象的。
					lockObj.wait();
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			System.out.print(Thread.currentThread().getName());
			energyBoxes[from] -= amount;
			System.out.printf("从%d转移%10.2f单位能量到%d", from, amount, to);
			energyBoxes[to] += amount;
			System.out.printf("能量总和:%10.2f%n",getTotalEnergies());
			
			//唤醒一个lockObj对象上等待的线程，这个不是在同一个线程，同一个操作中被执行的。
			lockObj.notify();
			
		}
	}
	
	/**
	 * 获取能量世界的能量总和
	 */
	public double getTotalEnergies() {
		double sum = 0;
		for (double amount : energyBoxes) {
			sum += amount;
		}
		return sum;
	}
	
	/**
	 * 返回能量盒子的长度
	 */
	public int getBoxAmount() {
		return energyBoxes.length;
	}
}
