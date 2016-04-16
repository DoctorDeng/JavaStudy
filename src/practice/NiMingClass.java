package practice;
/*
 * Description:  测试匿名类的作用
 */
abstract class Student{
	abstract void speak();
}

class Teacher{
	void look(Student stu){
		stu.speak();
	}
}
public class NiMingClass {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        Teacher teacher= new Teacher();
        teacher.look(new Student(){
        	void speak(){
        		System.out.println("这是匿名类中的方法！");
        	}
        });
	}

}
