package generics;

import java.time.LocalDate;

/**
 * 
 * TODO: 练习泛型的类型擦除
 * @author <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date {date} 下午9:35:12   
 * @version 1.0
 */
public class TestGengeicsErased {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		B b = new B();
		Pair<LocalDate> paid = b;
		String c = "ccc";
		String a = "aa" + "bb" + c;
	}

}
class Pair <T> {
	private T a;
	public void setA(T a) {
		this.a = a;
	}
	public T getA() {
		return a;
	}
}
class B extends Pair<LocalDate> {
	public void setA(LocalDate localDate) {
		
	}
}
