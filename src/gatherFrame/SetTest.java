package gatherFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * Description:   练习Set接口
 */
public class SetTest {

	public List<Course> coursesToSelect;
	
	private Scanner input;
	public Student student;
	
	public SetTest() {
		coursesToSelect = new ArrayList<Course>();
		input = new Scanner(System.in);
	}
	
	public void testAdd() {
		//创建一个课程对象，并通过调用add方法，添加到备选课程List中
		Course cr1 = new Course("1", "数据结构");
		coursesToSelect.add(cr1);
	    
		Course cr2 = new Course("2", "C语言");
		coursesToSelect.add(cr2);

		
		Course[] course = {new Course("3", "离散数学"), new Course("4", "高数")};
		coursesToSelect.addAll(Arrays.asList(course));
		
		Course[] course2 = {new Course("5", "大学英语"), new Course("6", "语文")};
		coursesToSelect.addAll(Arrays.asList(course2));

	}
	
	public void testForEach() {
		System.out.println("有如下课程待选(通过for earch访问):");
		for (Course courses : coursesToSelect) {
			Course cr = courses;
			System.out.print("课程:" + cr.getId() + ";" + cr.getName()+"\n");
		}
	}
	
	//创建学生对象并选课
	public void createStudentAndSelectCours() {
		
	    student = new Student("1", "小明");
		System.out.println("欢迎学生：" + student.getName() + "选课");
		
		Scanner input = new Scanner(System.in);
		for ( int i=0; i<6; i++) {
			System.out.println("请输入课程ID");
			if(input.hasNext()) {
				String courseId = input.next();
				
				for(Course cr : coursesToSelect) {
					if ( cr.getId().equals(courseId) ) {
						student.getCourses().add(cr);
						
						//Set中,添加某个对象，无论添加多少次，最终只会保留一个该对象的（引用）
						//并且保留的是第一次添加的那一个
						//并且，保留的是第一次添加的那一个
						//student.getCourses().add(cr);
					}
				}
			}
			else {
				System.out.println("输入错误，请重新输入!");
				i = i -1;
				continue;
			}
			
		}
		testForEachForSet(student);
	}
	
	/*
	 * 测试Set的contains方法
	 */
	public void testSetContains() {
		System.out.println("请输入学生已选的课程名称:");
		String name = input.nextLine();
		
		//创建一个新的课程对象，ID和名称，与course对象完全一样
		Course course2 = new Course();
		course2.setName(name);
		
		System.out.println("备选课程中是否包含课程:" + course2.getName() + 
	               "," + student.getCourses().contains(course2));
		
		if (coursesToSelect.contains(course2)) {
			System.out.println("课程: " + course2.getName() + "的索引位置为:" + coursesToSelect.indexOf(course2) );
		}
		
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		SetTest st = new SetTest();
		st.testAdd();
		//st.testListContains();
		st.testForEach();
		
		st.createStudentAndSelectCours();
		st.testSetContains();
		
//		st.testForEach();
//		Student student = new Student("1", "小明");
//		System.out.println("欢迎学生：" + student.getName() + "选课");
//		
//		Scanner input = new Scanner(System.in);
//		for ( int i=0; i<6; i++) {
//			System.out.println("请输入课程ID");
//			if(input.hasNext()) {
//				String courseId = input.next();
//				
//				for(Course cr : st.coursesToSelect) {
//					if ( cr.getId().equals(courseId) ) {
//						student.getCourses().add(cr);
//						
//						//Set中,添加某个对象，无论添加多少次，最终只会保留一个该对象的（引用）
//						//并且保留的是第一次添加的那一个
//						//并且，保留的是第一次添加的那一个
//						//student.getCourses().add(cr);
//					}
//				}
//			}
//			else {
//				System.out.println("输入错误，请重新输入!");
//				i = i -1;
//				continue;
//			}
//			
//		}
//		st.testForEachForSet(student);
		
	}
  
	//打印输出,学生所选的课程!
	public void testForEachForSet (Student student) {
		System.out.println("共选择了:" + student.getCourses().size() + "门课程");
//		for (Course cr : student.getCourses() ) {
//			System.out.println("选择了课程:" + cr.getId() + ":" + cr.getName() );
//		}
		Iterator<Course> cr = student.getCourses().iterator();
		while ( cr.hasNext() ) {
			Course cour = cr.next();
			System.out.println("选择了课程: " + cour.getId() + ":" + cour.getName() );
		}
	}
	
	/*
	 * 测试List的contains方法
	 */
	public void testListContains() {
		
		System.out.println("请输入课程名称:");
		String name = input.next();
		
		Course course2 = new Course();
		course2.setName(name);
		
		System.out.println("备选课程中是否包含课程:" + course2.getName() + 
	               "," + coursesToSelect.contains(course2));
		
		//创建一个新的课程对象，ID和名称，与course对象完全一样
//		//取得备选课程序列的第0个元素
//		Course course = coursesToSelect.get(0);
//		System.out.println("取得课程: " + course.getName());
//		System.out.println("备选课程中是否包含课程:" + course.getName() + 
//			               "," + coursesToSelect.contains(course));
//		
//		//虽然course2 和 course 属性和方法等完全相同，但却是不同的对象，故course2不包含在coursesToSelect中
//		Course course2 = new Course(course.getId(), course.getName() );
//		System.out.println("新创建课程: " + course2.getName() );
//		System.out.println("备选课程中是否包含课程:" + course2.getName() + 
//	               "," + coursesToSelect.contains(course2));
		
	}

	
}
