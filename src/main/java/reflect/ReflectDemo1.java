package reflect;

public class ReflectDemo1 {

	public static void main(String[] args) {
		Foo foo1 = new Foo();
		//第一种表示方式--》实际在告诉我们任何一个类都有一个隐含的静态成员变量class
		Class c1 = Foo.class;
		//第二种表达方式， 已知该类的对象通过getClass方法获得
		Class c2 = foo1.getClass();
		/**
		 * 官网：c1,c2表示了Foo类的类类型（class type）
		 * 类是对象，是Class类的实例对象
		 * 这个对象我们成为该类的类类型
		 */
		
		/**
		 * c1是等于c2的，这说明一个类只可能是Class类的一个实例对象
		 */
		System.out.println(c1==c2);
		
		//第三种表达方式
		Class c3 = null;
		try {
			c3 = Class.forName("javaReflectPractice.Foo");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(c2==c3);//结果为true
		
		try {
			//可以通过类的类类型创建该类的对象实例,但是该类必须要有无参数的构造方法
			Foo foo = (Foo)c1.newInstance();
			foo.print();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
class Foo{
	void print() {
		System.out.println("我是Foo类");
	}
}