package foundationEnhancement;

import java.sql.Date;

import javax.annotation.Resource;

/**
 * 练习 Java 反射
 * @author Doctor邓
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
		Class date  = Date.class;
		Date temp   = new Date(0);
		//3、通过类的示例对象获取
		Class date2 = temp.getClass();
	}
	
	Class class1 = Resource.class;
	Class class2 = new StaticImportTest().getClass();
}
