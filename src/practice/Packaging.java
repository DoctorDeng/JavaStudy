package practice;

/*
 * 功能：实现类的封装
 */
public class Packaging {
	private float screen;
	private int i;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public float getScreen() {
		return screen;
	}

	public void setScreen(float newScreen) {
		screen = newScreen;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Packaging hello = new Packaging();
		hello.setScreen(25.0f);
		System.out.println(hello.getScreen());
	}

}
