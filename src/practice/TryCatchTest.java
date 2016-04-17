package practice;
/*
 * Description:  测试try{}catch{}finally{}
 */
public class TryCatchTest {
	
	//测试try{}catch{}方法
	int test(){
		int divider=10;
		int result =100;
		try{
			while(divider>-1){
				divider--;
				result=result+ 100/divider;
			}
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("循环抛出异常!!");
			return -1;
		}
	}
	
	//测试try{}catch{}finally{}方法
	int test2(){
		int divider=10;
		int result =100;
		try{
			while(divider>-1){
				divider--;
				result=result+ 100/divider;
			}
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("循环抛出异常!!");
			return 999;
		}
		finally{
			System.out.println("这是finally！！！哈哈");
			System.out.println("我是resutl，我的值是："+result);
		}
	}
 
	
	//测试finally语句的执行顺序：在try{}catch{}的return语句之前执行的。
	int test3(){
		int divider=10;
		int result =100;
		try{
			while(divider>-1){
				divider--;
				result=result+ 100/divider;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("循环抛出异常!!");
		}
		finally{
			System.out.println("这是finally！！！哈哈");
			System.out.println("我是resutl，我的值是："+result);
		}
		System.out.println("我是test3我运行完了，不要想我哦！");
		return 1111;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
     
    /*
     * divider(除数):
     * result(结果)
     * try-catch捕获while循环
     * 每次循环，divider减一,result=result+100/divider
     * 如果捕获异常，打印输出"抛出异常了！！！",返回-1		
     * 否则：返回result
     */
	 
	 TryCatchTest tct= new TryCatchTest();
	 int result= tct.test();
	 System.out.println("异常信息返回值为:"+result);
	 
	 TryCatchTest tct1= new TryCatchTest();
	 int result1= tct.test2();
	 System.out.println("我想大声告诉你！test2执行完毕");
	 
	 TryCatchTest tct2= new TryCatchTest();
	 int result2= tct.test3();
	 System.out.println("我想大声告诉你！test3执行完毕");
	}
}
