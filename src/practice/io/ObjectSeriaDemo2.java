package practice.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 练习序列化中子类和父类构造函数的调用的问题
 */

//一个父类类实现了序列化接口，那么其子类都可以进行序列化
class Foo implements Serializable {
	public Foo() {
		System.out.println("foo...");
	}
}

class Foo1 extends Foo {
	public Foo1() {
		System.out.println("foo1...");
	}
}

class Foo2 extends Foo1 {
	public Foo2() {
		System.out.println("foo2...");
	}
}

class Bar {
	public Bar() {
		System.out.println("bar");
	}
}

class Bar1 extends Bar implements Serializable {
	public Bar1() {
		System.out.println("bar1...");
	}
}

class Bar2 extends Bar1 {
	public Bar2() {
		System.out.println("bar2...");
	}
}

public class ObjectSeriaDemo2 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ObjectOutputStream oos  = null;
		ObjectInputStream ois = null;
		Foo2 foo2;
		Bar2 bar2;
		
		try {
			//--------------父类实现Serializable序列化接口-------------------------//
			System.out.println("父类实现Serializable序列化接口");
			oos = new ObjectOutputStream(
					new FileOutputStream("demo/obj1.dat"));
			foo2 = new Foo2();
			oos.writeObject(foo2);
			
			//反序列化是否递归调用父类的构造函数
			ois = new ObjectInputStream(
					new FileInputStream("demo/obj1.dat"));
			foo2 = (Foo2)ois.readObject();
			System.out.println("反序列化输出: " + foo2);
			
			//------------------子类实现Serializable序列化接口--------------------------//
			System.out.println("子类实现Serializable序列化接口");
			bar2 = new Bar2();
			oos.writeObject(bar2);
			bar2 = (Bar2)ois.readObject();
			System.out.println("反序列化输出: " + bar2);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
