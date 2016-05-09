package practice.io;
/*
 * 用于和缓冲流练习的Student类
 */
public class Student {
	private int stuId;
	private String name;
	
	public Student(int stuId, String name) {
		this.stuId = stuId;
		this.name = name;
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
