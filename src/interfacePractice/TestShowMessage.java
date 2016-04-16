package interfacePractice;
/*
 * Description:  测试接口回调
 */
public class TestShowMessage {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
       IShowMessage show1= new TV();   //接口回调,即将实现接口的类的引用给予给接口对象
       show1.showTradeMark();
       
       IShowMessage show2= new PC();
       show2.showTradeMark();
	}

}
