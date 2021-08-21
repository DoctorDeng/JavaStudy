package foundationEnhancement;

import java.sql.Date;

/**
 * 练习 Java 反射
 * @author doctordeng
 *
 */
public class ReflectTest {
	
	public static void getClassWay() {
		try {
			//1、通过类名称加载类
			Class.forName("java.util.Date");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//2、通过每个类的静态成员变量
		Class<Date> date  = Date.class;
		Date temp   = new Date(0);
		//3、通过类的示例对象获取
		Class<? extends Date> date2 = temp.getClass();
	}
	
	public static void main(String[] args) {
		Class<String> str1= String.class;
		Class<Integer> int1 = int.class;
		System.out.println(int1.isPrimitive());
		
		int[] ints = new int[1];
		Class<? extends int[]> classInt = ints.getClass();
		//判断是否是数组
		classInt.isArray();
	}
}
