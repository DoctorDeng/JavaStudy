package gatherFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/*
 * Description:   备选课程类
 */
public class ListTest {
	
	//用于存放备选课程的List
	public List coursesToSelect;
	
	public ListTest() {
		this.coursesToSelect = new ArrayList();
	}
	

	public void testAdd() {
		//创建一个课程对象，并通过调用add方法，添加到备选课程List中
		Course cr1 = new Course("1", "数据结构");
		coursesToSelect.add(cr1);
		Course temp = (Course)coursesToSelect.get(0);
		System.out.println("添加了课程:" + temp.getId() + ":" + temp.getName());
	    
		Course cr2 = new Course("2", "C语言");
		coursesToSelect.add(0,	cr2);
		Course temp2 = (Course)coursesToSelect.get(0);
		System.out.println("添加了课程:" + temp2.getId() + ":" + temp2.getName());
	
		//以下方法会抛出数据小标越界异常
//		Course cr3 = new Course("3", "test");
//		coursesToSelect.add(4, cr3);
		
		Course[] course = {new Course("3", "离散数学"), new Course("4", "高数")};
		coursesToSelect.addAll(Arrays.asList(course));
		Course temp3 = (Course)coursesToSelect.get(2);
		Course temp4 = (Course)coursesToSelect.get(3);
		System.out.println("添加了两门课程：" + temp3.getId() + ":" + temp3.getName()
		                   + ";" + temp4.getId() + ":" + temp4.getName());
		
		Course[] course2 = {new Course("5", "大学英语"), new Course("6", "语文")};
		coursesToSelect.addAll(2, Arrays.asList(course2));
		Course temp5 = (Course)coursesToSelect.get(2);
		Course temp6 = (Course)coursesToSelect.get(3);
		System.out.println("添加了两门课程：" + temp5.getId() + ":" + temp5.getName()
        + ";" + temp6.getId() + ":" + temp6.getName());
	}
	
	//往List中添加一些奇怪的东西
	public void testType() {
		System.out.println("能否往List中添加一些奇怪的东西");
		coursesToSelect.add("我不是课程,我只是一个无辜的字符！");
	}
	
	public static void main(String[] args) {
		ListTest lt = new ListTest();
		lt.testAdd();
//		lt.testType();
		lt.testForEach();
//		lt.testGet();
//		lt.testIterator();
//		lt.testForEach();
//		lt.testModify();
//		lt.testForEach();
//		lt.testRemove();
	}
	
	//取得List中的元素的方法
	public void testGet() {
		int size = coursesToSelect.size();
		
		for(int i=0; i< size; i++) {
			Course cr = (Course)coursesToSelect.get(i);
			System.out.println("课程:" + cr.getId() + ";" + cr.getName());
		}
	}
	
	//通过迭代器来遍历List
	public void testIterator() {
		Iterator it = coursesToSelect.iterator();
		System.out.println("有如下课程待选(通过迭代器访问):");
		while (it.hasNext()) {
			Course cr = (Course)it.next();
			System.out.print("课程:" + cr.getId() + ";" + cr.getName()+"\n");
		}
	}
	
	//通过for each 方法访问集合元素
	public void testForEach() {
		System.out.println("有如下课程待选(通过for earch访问):");
		for (Object obj:coursesToSelect) {
			Course cr = (Course) obj;
			System.out.print("课程:" + cr.getId() + ";" + cr.getName()+"\n");
		}
	}
	
	//修改List中的元素
	public void testModify() {
		coursesToSelect.set(4, new Course("388", "aaa"));
	}
	
	//删除List中的元素
	public void testRemove() {
		//Course cr = (Course)coursesToSelect.get(1);
		//System.out.print("我是课程:" + cr.getId() + ";" + cr.getName()+"   我即将被删除\n");
		System.out.println("即将删除2位置和3位置上的课程！");
		Course[] courses = {(Course)coursesToSelect.get(2), (Course)coursesToSelect.get(3)};
		coursesToSelect.removeAll(Arrays.asList(courses));
		//coursesToSelect.remove(1);
		System.out.println("成功删除课程！");
		testForEach();
	}
}
