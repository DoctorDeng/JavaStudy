import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/*
 * Description:  练习专用类
 */

class Student {
	String name;
	Student(String name) {
		this.name = name;
	}
	
}

public class Practice {
	
	
	public static void main(String[] args) {
		HashSet<Student> stu = new HashSet<Student>();
		Student stu1 = new Student("1");
		Student stu2 = new Student("2");
		Student stu3 = new Student("3");
		Student stu4 = new Student("4");
		Student stu5 = new Student("5");
		Student stu6 = new Student("6");
		stu.add(stu2);
		
		
		stu.add(stu3);
		stu.add(stu4);
		stu.add(stu5);
		stu.add(stu1);
		
		stu.add(stu6);
		
		for (Student temp : stu) {
			System.out.println(temp.name);
		}
	
	}
}
