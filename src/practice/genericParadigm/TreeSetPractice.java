package practice.genericParadigm;
/*
 * Description:  联系TreeSet<E>泛型类
 */

import java.util.*;

class Student1 implements Comparable{
	int english = 0;
	String name;
	Student1(int english, String name){
		this.english = english;
		this.name = name;
	}
	
	public int compareTo(Object obj){
		Student1 stu = (Student1)obj;
		return (this.english - stu.english);
	}
}
public class TreeSetPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		TreeSet<Student1> stuTree = new TreeSet<Student1>();
		Student1 stu1 = new Student1(95, "张三"),
		         stu2 = new Student1(63, "李四"),
		         stu3 = new Student1(96, "王五"),
		         stu4 = new Student1(65, "赵六");
		
		stuTree.add(stu1);
		stuTree.add(stu2);
		stuTree.add(stu3);
		stuTree.add(stu4);
		
		Iterator<Student1> ite = stuTree.iterator();
		while(ite.hasNext()){
			Student1 temp = ite.next();
			System.out.println(""+ temp.name+ " "+ temp.english);
		}
	}

}
