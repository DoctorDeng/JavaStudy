package coreJavaPractice.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Set;

import gatherFrame.Course;
import gatherFrame.Student;
/**
 * 练习泛型
 * @author DoctorDeng
 *
 */
public class ReflectTest {
	/**
	 * 获取对象指定名称的属性
	 * @param targetObject 目标对象
	 * @param name  要获取的对象的属性名
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> T getFiled(Object targetObject , String name) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if (null == targetObject ) return null;
		
		Field field = targetObject .getClass().getDeclaredField(name);
		// 让 private 修饰的 Feild 能够访问
		field.setAccessible(true);
		return (T)field.get(targetObject );
	}
	
	public static void printObject(Object object) {
		ArrayList<Integer> squares = new ArrayList<>();
		for (int i = 0; i < 5; i++) squares.add(i);
		System.out.println(new ObjectAnalyzer().toString(squares));
	}
	
	public static void main(String[] args) {
		Student stu = new Student("12", "邓华杰");
		try {
			Set<Course> name = getFiled(stu, "courses");
			System.out.println(name);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} 
	}

}
