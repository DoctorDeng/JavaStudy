package practice;
/*
 * 测试继承的测试类
 */
public class TestInherit {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//创建一个孩子对象
        ChildClass child=new ChildClass();
        //由于孩子类继承父亲类，顾其有父类的方法和属性，顾下面可以调用
        child.name="邓华杰";
        child.eat();
	}

}
