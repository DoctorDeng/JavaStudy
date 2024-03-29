package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ReflectGenericityDemo {
	public static void main(String[] args) {
		ArrayList list = new ArrayList<>();
		
		ArrayList<String> list1 = new ArrayList<>();
		list1.add("hello");
		//list1.add(20);错误的
		Class c1 = list.getClass();
		Class c2 = list1.getClass();
		System.out.println(c1 == c2);
		//反射的操作都是编译之后的操作
		/**
		 * c1 == c2结果返回true说明编译之后集合的泛型是去泛型话的。
		 * Java中集合的泛型，是防止错误输入的，只在编译阶段有效，
		 * 绕过编译无效
		 * 验证：我们可以通过方法的反射来操作，绕过编译
		 */
		try {
			Method m = c1.getMethod("add", Object.class);
			m.invoke(list1, 20);//绕过编译操作就绕过了泛型
			System.out.println(list1.size());
			System.out.println(list1);
			/**
			 * for(String base.string:list1) {
			 * 	 System.out.println(base.string);
			 * }
			 */ // 现在不能这样遍历
			for (int i=0;i<list1.size();i ++) {
				
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
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
