package abstractPractice;

public class SmartPhone extends Telphone {

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
