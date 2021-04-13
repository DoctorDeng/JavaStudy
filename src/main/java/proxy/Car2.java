package proxy;

public class Car2 extends Car {

	@Override
	public void mone() {
		long startTime = System.currentTimeMillis();
		System.out.println("汽车开始行驶。。。。");
		super.mone();
		long endTime = System.currentTimeMillis();
		System.out.println("汽车行驶结束...汽车行驶时间:" + (endTime - startTime) + "毫秒");
	}
	
}
