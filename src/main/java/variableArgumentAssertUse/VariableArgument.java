package variableArgumentAssertUse;
/*
 * Description:   可变参数的使用
 *         语法:   public void f(int...x)
 *         说明:   f()的参数类型是int类型，但个数不确定。这里称x是一个“代表参数”。
 *                参数代表可以通过下标运算表示参数列表中的具体参数，如x[0]和x[1]分别
 *                代表参数列表中的第一个参数和第二个参数,x.length等于参数的个数。
 */

class A{
	public void f(int ... x){
		int sum = 0;
		for(int i=0;i<x.length;i++){
			sum = sum + x[i];
		}
		
		System.out.println(sum);
	}
	
	public void g(String ... s){
		for(int i=0; i<s.length; i++){
			System.out.printf("%s", s[i]);
		}
	}
}
public class VariableArgument {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        A a = new A();
        a.f(1,2,3,4,5);
        a.f(-1,-2,-3,-4,-5);
        a.g("how","are","you");
	}

}
