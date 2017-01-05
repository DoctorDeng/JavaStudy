package coreJavaPractice.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
			PrintClassInfo.printConstructor(constructor);
		}
	}
	/**
	 * 打印 Constructor 信息
	 * @param constructor Constructor 
	 */
	public static void printConstructor(final Constructor<?> constructor) {
		if (null == constructor) return;
		
		String constructorName = constructor.getName();
		System.out.print("  ");
		// 获取 Method 修饰符
		String modifiers = Modifier.toString(constructor.getModifiers());
		if (modifiers.length() > 0) System.out.print(modifiers + " ");
		
		System.out.print(constructorName + "(");
		Class<?>[] paramTypes = constructor.getParameterTypes();
		for (int i = 0, len = paramTypes.length; i < len; i++) {
			if (i > 0) System.out.print(", ");
			System.out.print(paramTypes[i].getSimpleName());
		} 
		
		System.out.println(");");
	}
	/**
	 * 打印 Class 的所有方法
	 * @param clazz Class
	 */
	public static void printMethods(final Class<?> clazz) {
		if (null == clazz) return;
		
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			PrintClassInfo.printMethod(method);
		}
	}
	
	public static void printMethod(final Method method) {
		if (null == method) return;
		
		Class<?> returnType = method.getReturnType();
		String methodName   = method.getName();
		// 获取 Method 修饰符
		String modifiers = Modifier.toString(method.getModifiers());
		System.out.print("  ");
		if (modifiers.length() > 0) System.out.print(modifiers + " ");
		if (null != returnType) System.out.print(returnType.getSimpleName() + " ");
		
		System.out.print(methodName + " (");
		Class<?>[] paramTypes = method.getParameterTypes();
		for (int i = 0, len = paramTypes.length; i < len; i++) {
			if (i > 0) System.out.print(", ");
			System.out.print(paramTypes[i].getSimpleName());
		}
		System.out.println(");");
	}
	/**
	 * 打印 Class 的所有属性
	 * @param clazz Class
	 */
	public static void printFields(final Class<?> clazz) {
		if (null == clazz) return;
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			PrintClassInfo.printField(field);
		}
	}
	/**
	 * 打印 Filed 信息
	 * @param field
	 */
	public static void printField(final Field field) {
		if (null == field) return;
		
		Class<?> fieldType = field.getType();
		String fieldTypeName = fieldType.getSimpleName();
		// 打印修饰符
		String modifers = Modifier.toString(field.getModifiers());
		System.out.print("  ");
		if (modifers.length() > 0) System.out.print(modifers + " ");
		System.out.print(fieldTypeName + " " + field.getName() + ";");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintClassInfo.printClass("java.lang.String");
		/*Student stu = new Student("你好", 0, false);
		Student[] stus = {stu};
		System.out.println(stus.getClass().getSimpleName());*/
		
	}

}
