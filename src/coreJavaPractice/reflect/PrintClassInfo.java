package coreJavaPractice.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
/**
 * 打印类的相关信息
 * @author DoctorDeng
 *
 */
public class PrintClassInfo {
	/**
	 * 打印一个类的信息
	 * @param className  类名
	 */
	public static void printClass(final String className){
		if (null == className || className.length() == 0) {
			System.out.println("null");;
			return;
		}
		
		try {
			Class<?> clazz      = Class.forName(className);
			Class<?> superClazz = clazz.getSuperclass();
			
			String modifiers = Modifier.toString(clazz.getModifiers());
			if (modifiers.length() > 0) System.out.print(modifiers + " ");
			System.out.print("class " + className);
			if (null != superClazz && superClazz != Object.class) {
				System.out.print(" extends " + superClazz.getName());
			}
			System.out.print("\n{\n");
			
			PrintClassInfo.printConstructors(clazz);
			PrintClassInfo.printFields(clazz);
			PrintClassInfo.printMethods(clazz);
			
			System.out.println("}");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 打印 Class 的所有构造方法
	 * @param clazz Class
	 */
	public static void printConstructors(final Class<?> clazz) {
		if (null == clazz) return;
		
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> constructor : constructors) {
			
		}
	}
	/**
	 * 打印 Class 的所有方法
	 * @param clazz Class
	 */
	public static void printMethods(final Class<?> clazz) {
		
	}
	/**
	 * 单元 Class 的所有属性
	 * @param clazz Class
	 */
	public static void printFields(final Class<?> clazz) {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintClassInfo.printClass("java.lang.String");
	}

}
