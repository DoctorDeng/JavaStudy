package thread;

import java.util.Random;


public class TestThreadLocal {

	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
	private static ThreadLocal<Student> stuThreadLocal = new ThreadLocal<>();
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Student student = new Student("邓华杰", 20);
				stuThreadLocal.set(student);
				System.out.println(Thread.currentThread().getName() + "放入数据：" + student.toString());
				new A().getData();
				new B().getData();
			}
		}).start();

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Student student = new Student("熊勇", 25);
				stuThreadLocal.set(student);
				System.out.println(Thread.currentThread().getName() + "放入数据：" + student.toString());
				new A().getData();
				new B().getData();
			}
		}).start();
	}

	static class A{
		public void getData(){
			Student stu = stuThreadLocal.get();
			System.out.println("A 从" + Thread.currentThread().getName() + "线程，取出数据：" + stu.toString());
		}
	}
	
	static class B{
		public void getData(){
			Student stu = stuThreadLocal.get();
			System.out.println("B 从" + Thread.currentThread().getName() + "线程，取出数据：" + stu.toString());
		}
	}
	
}

class Student {
	private String name;
	private int age;
	
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
}