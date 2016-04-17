package practice;
/*
 * Description:  练习异常链(多个异常一环接一环)
 */
public class TryCatchPractice2 {
	/*
	 * test1():抛出"喝大了"异常
	 * test2():调用test1(),捕获“喝大了”异常，并且包装运行时异常，继续抛出
	 * main方法中，调用test2(),尝试捕获test2()方法抛出的异常
	 */
	
	
	

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		TryCatchPractice2 trycatch= new TryCatchPractice2();
		trycatch.test2();
	}
	
	class DrunkException extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7180374787085894146L;

		DrunkException(String message){
			System.out.println(message);
		}
	}
	
	public void test1() throws DrunkException{
		throw new DrunkException("开车别喝酒!");
	}
	
	public void test2(){
		try{
			test1();
			System.out.println("没有发生异常");
		}
		catch(DrunkException e){
			RuntimeException newExc= 
					new RuntimeException("司机一滴酒,亲人两行泪！");
        newExc.initCause(e); //initCause()的作用你可以理解成是保存原始的异常
        throw newExc;
		}
	}
	

} 
