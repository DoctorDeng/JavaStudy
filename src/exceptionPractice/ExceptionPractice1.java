package exceptionPractice;
/*
 * Description:   练习 异常
 */
class MyException extends Exception{
	private int id;
	
	public MyException (String message, int id) {
		super (message);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}

class Test {
	public void regist (int num) throws MyException {
		if (num < 0){
			throw new MyException ("人数为负值, 不合理", 3);
		}
		System.out.println("登记人数 "+ num);
	}
	
	public void manager() {
		try {
			regist(-10);
		} catch (MyException e) { 
			System.out.println ("登记失败, 出错类型码=" + e.getId());
			e.printStackTrace();
		}
		System.out.print("操作结束");
	}
}

public class ExceptionPractice1 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Test t = new Test();
		t.manager();
	}

}
