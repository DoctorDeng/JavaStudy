package practice;
/*
 * 测试继承的子类
 */
public class ChildClass extends FatherClass {
	@Override
	public String toString() {
		return "ChildClass [age=" + age + "]";
	}
	//方法的重写
	public int age=50;
	public void eat(){
		System.out.println("年龄:"+age+" doctordeng吃东西特别厉害");
	}
	public ChildClass(){
		super();
		System.out.println("孩子类构造方法执行了");
	}
	public void method(){
		System.out.println("父类的年龄为:"+super.age);
		System.out.println("子类的年龄为:"+age);
		System.out.println("下面是父类的eat()方法！");
		super.eat();
		System.out.println("子类重写的toString()方法: "+this.toString());
	}
}
