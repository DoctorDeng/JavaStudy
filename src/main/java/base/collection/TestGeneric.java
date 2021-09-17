package base.collection;

import java.util.ArrayList;
import java.util.List;

/*
 * Description:   练习泛型
 */
public class TestGeneric {

	//带有泛型---Course，的List类型属性
	public List<Course> courses;
	
	public TestGeneric () {
		this.courses = new ArrayList<Course>();
	}
	
	//测试添加
	public void testAdd() {
		Course cr1 = new Course("1", "大学语文");
		courses.add(cr1);
		
		//泛型集合中，不能添加泛型规定的类型及其子类型以外的对象。
//		courses.add("能否添加字符串");
		
		Course cr2 = new Course("2", "大学英语");
		courses.add(cr2);
	}
	
	//测试循环遍历
	public void testForEach() {
		for(Course cr : courses) {
			System.out.println(cr.getId() + ";" + cr.getName());
		}
	}
	
	//泛型集合可以添加泛型的子类型的对象实例
	public void testChild() {
		ChildCourse ccr = new ChildCourse("3","我是子类型的一个实例~~");
		courses.add(ccr);
	}
	
	//泛型不能使用基本类型
	public void testBasicType() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(345);
		System.out.println("基本类型必须使用包装类作为泛型: " + list.get(0));
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		TestGeneric test = new TestGeneric();
		test.testAdd();
		test.testChild();
		test.testForEach();
		test.testBasicType();
	}

}
