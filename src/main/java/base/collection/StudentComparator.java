package base.collection;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
	
	public int compare(Student stu1, Student stu2) {
		
		return stu1.getName().compareTo(stu2.getName());
	}
	
}
