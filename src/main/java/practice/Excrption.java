package practice;
/*
 * Description:  联系异常类和自定义异常类：try{} catch{}
 */
//自定义异常类
class MyException extends Exception{
	String message;
	MyException(int n){
		message=n+"不是正整数";
	}
	
	public String getMessage(){
		return message;
	}
}

class A{
	public void f(int n) throws MyException{
		if(n<0){
			MyException ex= new MyException(n);
			throw(ex);
		}
		double number=Math.sqrt(n);
		System.out.println(n+"的平方根结果为:"+number);
	}
}

public class Excrption {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//测试异常类
		try{
			int m=Integer.parseInt("5555dsfds");
			System.out.println("没有发生异常!");
		}
		catch(Exception e){
			System.out.println("发生异常！");
		}
		
		//测试自定义异常类
		A a= new A();
		try{
			a.f(-1);
		}
		catch(MyException e){
			System.out.println(e.getMessage());
		}

	}

}
