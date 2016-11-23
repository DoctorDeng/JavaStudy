package foundationEnhancement;

import java.io.BufferedReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 练习 Constructor 类
 * @author Doctor邓
 *
 */
public class ConstructorTest {
	
	public static void constructorTest() {
		Class<String> stringClass = String.class;
		try {
			//获取类所有的构造方法
			Constructor<?>[] strCons = stringClass.getConstructors();
			//获取单个构造方法, 源码运用了 JDK 1.5的可变参数, 所以这里可以写很多个参数
			Constructor<String> strConS = stringClass.getConstructor(BufferedReader.class);
			
			for (int i = 0, len = strCons.length; i < len; i++) {
				Constructor<?> strCon = strCons[i];
			    
				Class<?>[] paraTypes = strCon.getParameterTypes();
			    for (int j =0, leng = paraTypes.length; j < leng; j++) {
			    	System.out.print(paraTypes[j].getSimpleName()+ "  " + 
			    			paraTypes[j].toString() + "--");
			    }
			    
				System.out.print(strCon.getParameterCount());
				System.out.println();
			}
		} catch (SecurityException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public static void createClassByConstructor() {
		try {
			//获取 String(String paramter) 构造类实例
			Constructor<String> con = String.class.getConstructor(String.class);
			// 使用 Constructor实例 构造 String 类的实例
			String temp = con.newInstance("11");
			System.out.println(temp);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static void createClassByClass() {
		try {
			//使用无参构造方法创建对象
			String str = String.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//constructorTest();
		createClassByConstructor();
	}
	
}
