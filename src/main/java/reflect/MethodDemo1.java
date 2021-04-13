package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodDemo1 {

	public static void main(String[] args) {
		/**
		 * 要获取print(int,int)方法
		 * 获取方法就是要获取类的信息，获取类的信息首先要获取类的类类型
		 */
		AAA a1 = new AAA();
		Class c = a1.getClass();
		/**
		 * 获取方法,名称和参数列表来决定方法
		 * getMethod获取的是public的方法
		 * getDeclaredMehod获取自己声明的 方法
		 */
		try {
			//Method  m = c.getMethod("pribt",new Class[]{int.class, int.class});
			Method  m = c.getMethod("print", int.class,int.class);
			
			//方法的反射操作,是使用m方法对象来进行方法调用和a1.print（）效果完全相同
			//方法如果没有返回值返回null,有返回值返回具体的返回值
			//Object o = m.invoke(a1, new Object[]{10,20});
			Object o = m.invoke(a1, 10,20);
			System.out.println("==============");
			Method m1 = c.getMethod("print", String.class,String.class);
			o = m1.invoke(a1, "aa","bb");
			System.out.println("==============");
			//Method m2 = c.getMethod("print",new Class[]{});
			Method m2 = c.getMethod("print");
			o = m2.invoke(a1);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

class AAA{
	
	public void print(){
		System.out.println("HelloWorld");
	}
	public void print(int a, int b) {
		System.out.println(a + b);
	}
	public void print(String a, String b) {
		System.out.println(a.toUpperCase() + "," + b.toLowerCase());
	}
}