package foundationEnhancement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 使用反射调用其他类的 Main 方法
 * @author Doctor邓
 *
 */
public class MethodPractice {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		if (scanner.hasNext()) {
			String className = scanner.next();
			
			try {
				Class<?> clas = Class.forName(className);
				Method mainMethod = clas.getMethod("main", String[].class);
				/**
				 * JDK 1.5 为了兼容 1.4 版本, 会将传如的数组分离开, 将数组中的每个元素当做一个参数, 
				 * 而不是将数组当做一个整体作为一个参数, 所以使用如下方法无法调用 Main 方法
				 * mainMethod.invoke(null, new String[]{"1","2","3"});
				 */
				mainMethod.invoke(null, new Object[]{new String[]{"2","1"}});
				//这种方法也可以, 将数组转换为一个 Object 对象
				//mainMethod.invoke(null, (Object)new String[]{"2","1"});
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} 
	}
}
class TestClass {
	public static void main(String[] args) {
		for (String arg : args) {
			System.out.println(arg);
		}
	}
}
