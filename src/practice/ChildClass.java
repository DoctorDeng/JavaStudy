package practice;
/*
 * 测试继承的子类
 */
public class ChildClass extends FatherClass {
	//方法的重写
	public void eat(){
		System.out.println("年龄:"+age+" 邓华杰吃东西特别厉害");
	}
	public ChildClass(){
		System.out.println("孩子类执行了");
	}
}
