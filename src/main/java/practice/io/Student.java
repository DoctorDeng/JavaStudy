package practice.io;

import java.io.Serializable;

/*
 * 用于和缓冲流练习的Student类
 */
public class Student implements Serializable {
	private transient int stuId;
	private String name;
	
	public Student(int stuId, String name) {
		super();
		this.stuId = stuId;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", name=" + name + "]";
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
	
	private void writeObject(java.io.ObjectOutputStream s)
		throws java.io.IOException{
		s.defaultWriteObject(); //把jvm能默认序列化的元素进行序列化
		s.writeInt(stuId);    //自己完成 stuId 序列化
	}

	private void readObject(java.io.ObjectInputStream s)
		throws java.io.IOException, ClassNotFoundException {
		s.defaultReadObject(); //吧jvm能默认反序列化的元素进行反序列化
		this.stuId = s.readInt(); //自己完成stuId的反序列化操作。
	}
}
