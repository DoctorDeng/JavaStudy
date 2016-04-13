package abstractPractice;

public class SmartPhone extends Telphone implements IPlayGame{

	@Override
	public void playGame() {
		// TODO 自动生成的方法存根
		System.out.println("具有玩游戏的功能");
	}

	@Override
	public void call() {
		// TODO 自动生成的方法存根
        System.out.println("通过语音打电话");
	}

	@Override
	public void message() {
		// TODO 自动生成的方法存根
       System.out.println("通过语音发短信");
	}

}
