package proxyPractice;

public class CarTimeProxy implements Moveable {
	private Moveable move;
	
	public CarTimeProxy(Moveable move) {
		super();
		this.move = move;
	}
	@Override
	public void mone() {
		long startTime = System.currentTimeMillis();
		System.out.println("汽车开始行驶。。。。");
		move.mone();
		long endTime = System.currentTimeMillis();
		System.out.println("汽车行驶结束...汽车行驶时间:" + (endTime - startTime)+":毫秒!");
	}

}
