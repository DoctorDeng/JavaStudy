package corejava.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 练习反射中的 Method
 * @author doctordeng
 *
 */
public class MethodTableTest {
	
	public static double square(double x) {
		return x * x;
	}
	
	public static void printTable(double start, double end, int rowNum, Method method) {
		System.out.println(method);
		double step = (end - start) / (rowNum -1);
		
		for (double x = start; x <= end; x+= step)
		{
			try {
				double y = (Double) method.invoke(null, x);
				System.out.printf("%10.4f | %10.4f%n", x, y);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	public static void main(String[] args) {
		try {
			Method square = MethodTableTest.class.getMethod("square", double.class);
			Method sqrt   = Math.class.getMethod("sqrt", double.class);
			
			MethodTableTest.printTable(1, 10, 10, square);
			MethodTableTest.printTable(1, 10, 10, sqrt);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
