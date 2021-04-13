package practice;

public class test {
	String name="我爱慕课网";
	static String hobby ="imooc";
	public static void printOut(){
		System.out.println("欢迎来到慕课网!");
	}
	public void show(){
		System.out.println("欢迎您:"+name+"!");
		System.out.println("爱好:"+hobby+"!");
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        printOut();
        test hello=new test();
        hello.show();
	}

}
