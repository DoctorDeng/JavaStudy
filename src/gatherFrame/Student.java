package gatherFrame;
import java.util.HashSet;
/*
 * Description:   学生类
 */
import java.util.Set;
public class Student {
	private String id;
	private String name;
	private Set courses;
	
	public Student(String id, String name) {
		this.id = id;
		this.name = name;
		this.courses = new HashSet();
	}
}
