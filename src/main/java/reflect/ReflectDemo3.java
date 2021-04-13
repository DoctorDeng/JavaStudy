package reflect;

public class ReflectDemo3 {
	
	public static void main(String[] args) {
		Class c1 = int.class;//int的类类型
		Class c2 = String.class;//String类的类类型（String类字节码）
		Class c3 = double.class;//两个不相同
		Class c4 = Double.class;
		Class c5 = void.class;
		
		System.out.println(c1.getName());
		//类的全称（带包名）
		System.out.println(c2.getName());
		//不带报名的类的名称
		System.out.println(c2.getSimpleName());
	}

}
