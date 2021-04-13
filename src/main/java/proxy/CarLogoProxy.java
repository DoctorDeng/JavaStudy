package proxy;

public class CarLogoProxy implements Moveable {
	private Moveable move;
	
	public CarLogoProxy(Moveable move) {
		super();
		this.move = move;
	}
	@Override
	public void mone() {
		System.out.println("日志开始...");
		move.mone();
		System.out.println("日志结束...");
	}

}
