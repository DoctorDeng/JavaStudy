package practice;
/*
 * 功能：测试继承
 * 测试继承的父类
 */
public class FatherClass {
	public int age=20;
	public String name;
    public void eat(){
		System.out.println("人有吃东西的能力");
	}
	public FatherClass(){
		System.out.println("父类方法执行了");
		age=30;
	}
}

