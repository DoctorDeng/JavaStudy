package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * 1、通过Collections.sort() 方法，对List 集合和泛型进行排序。
 * 例如：对Integer泛型的List排序、对String泛型的List进行排序。
 * 对其他类型泛型的List进行排序，以Student为例
 */

public class CollectionsTest {

	/*
	 * 1、通过Collection.sort() 方法，对Integer泛型的List进行排序
	 */
	public void testSort1() {
		List<Integer> integerList = new ArrayList<Integer>();
		Random random = new Random();
		Integer k;

		for ( int i = 0; i < 10; i++) {
			do { k = random.nextInt(100); }
			while ( integerList.contains(k) );
			integerList.add(k);
			System.out.println("成功添加整数: " + k);
		}
		
		System.out.println("---------------排序前------------");
		for (Integer integer : integerList) {
			System.out.println("元素:" + integer);
		}
		
		Collections.sort(integerList);
		System.out.println("---------------排序后------------");
		for (Integer integer : integerList) {
			System.out.println("元素:" + integer);
		}
	}
	
	/*
	 * 对String泛型的List进行排序
	 */
	public void testSort2() {
		List<String> stringList = new ArrayList<String>();
		stringList.add("google");
		stringList.add("tecent");
		stringList.add("baidu");
		
		System.out.println("---------------排序前------------");
		for (String string : stringList) {
			System.out.println("元素:" + string);
		}
		
		Collections.sort(stringList);
		System.out.println("---------------排序后------------");
		for (String string : stringList) {
			System.out.println("元素:" + string);
		}
	}
	
	/*
	 * 3、对其他类型泛型的List进行排序，以Student为例。
	 */
	public void testSort3() {
		List<Student> studentList = new ArrayList<Student>();
		Random random = new Random();
		
		studentList.add(new Student(random.nextInt(100) + "" ,"小明") );
		studentList.add(new Student(random.nextInt(100) + "" ,"小红") );
		studentList.add(new Student(random.nextInt(100) + "" ,"小兰") );
		studentList.add(new Student(random.nextInt(100) + "" ,"小华") );
		studentList.add(new Student(random.nextInt(100) + "" ,"小钱") );
		
		System.out.println("-------------------排序前------------");
		for (Student student : studentList) {
			System.out.println("学生:" + student.getId() + ": " + student.getName());
		}
		Collections.sort(studentList);
		
		System.out.println("-------------------排序后------------");
		for (Student student : studentList) {
			System.out.println("学生:" + student.getId() + ": " + student.getName());
		}
		Collections.sort(studentList,new StudentComparator() );
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		CollectionsTest ct = new CollectionsTest();
		//ct.testSort1();
		//ct.testSort2();
		ct.testSort3();
		
	}

}
