package practice;
//外部类InnerClass
public class InnerClass {
	//外部类私有属性
	private String name="imooc";
	//外部类成员属性
	int age=20;
	//内部类
	public class Inner{
		String name="爱慕课";
		//内部类Inner的方法
		public void show(){
			System.out.println("外部类中的name:"+InnerClass.this.name);
			System.out.println("内部类中的name:"+name);
			System.out.println("外部类中的age:"+age);
		}
	}

	public static void main(String[] args) {
		//创建外部类的对象
        InnerClass hello=new InnerClass();
        //创建内部类的对象
        Inner inn=hello.new Inner();
        //调用内部类对象的方法
        inn.show();
	}

}
