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
		Student stu = new Student("1");
		System.out.println(stu.name);
		change(stu, "2");
		System.out.println(stu.name);
	}
    public  static void change(Student student, String name) {
    	student.name = name;
    } 
}
