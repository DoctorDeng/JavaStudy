package practice;
/*
 * 功能：测试继承
 * 测试继承的父类
 */
public class FatherClass {

	@Override
	public boolean equals(Object obj) {
		//判断两个对象
		if (this == obj)
			return true;
		//判断对象是否为空
		if (obj == null)
			return false;
		//判断两个对象的类型是否相同
		if (getClass() != obj.getClass())
			return false;
		//通过FatherClass创建一个对象使其等于obj然后再来判断两个对象的属性是否相同
		FatherClass other = (FatherClass) obj;
		if (age != other.age)
			return false;
		return true;
	}
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

