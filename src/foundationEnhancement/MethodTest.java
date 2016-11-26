package foundationEnhancement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 练习反射中的 Method 类
 * @author Doctor邓
 *
 */
public class MethodTest {

	public static void staticMethod(String a) {
		System.out.println("这是一个静态方法, String:" + a);
	}
	
	public static void methodTest() {
		MethodTest methodTest = new MethodTest();
		Class<? extends MethodTest> mClass = methodTest.getClass();
		try {
			Method staticMethod = mClass.getMethod("staticMethod", String.class);
			staticMethod.invoke(null, "你好");
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	public static void stringCharAtMehtod() {
		try {
			String str  = new String("Hello World, Doctor邓");
			/**
			 * 从某个 Class 上获取某个 Method, 注意：Method 和 Field 一样并不是
			 * 属于某个具体对象的而是属于某个 Class 的。
			 * 获取 Method 时, 第一个参数是方法名称, 后面的参数使用了可变参数, 具体为方法的参数类型的类类型
			 */
			Method methodCharAt = String.class.getMethod("charAt", int.class);
			char newStr = (char) methodCharAt.invoke(str, 1);
			System.out.println(newStr);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		stringCharAtMehtod();
		methodTest();
	}
}
