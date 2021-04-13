package foundationEnhancement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValueTransmitTest {
	static int a = 1;
	static String b = "2";
	static int[] c = {3,4};
	static String[] d = {"5","6"};
	static A e = new A();

	public static void main(String[] args) {
		valueTransmitTest(a, b, c, d, e);
		Object obj = new Object();
	}
	
	public static void valueTransmitTest(int a, String b, int[] c, String[] d, A e) {
		a = 11;
		System.out.println(ValueTransmitTest.a);
		b = "12";
		System.out.println( b == ValueTransmitTest.b);
		c[0] = 11;
		System.out.println( c == ValueTransmitTest.c);
		d = new String[3];
		System.out.println(Arrays.toString(ValueTransmitTest.d));
		System.out.println( e == ValueTransmitTest.e);
	}

}
class A {
	private int a = 1;
	private Map<String, String> map = new HashMap<>();
	public A(){
		
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	@Override
	public String toString() {
		return "A [a=" + a + ", map=" + map + "]";
	}
}
