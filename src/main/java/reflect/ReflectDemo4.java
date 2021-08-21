package reflect;
/**
 * 获取类的信息示例
 * @author doctordeng
 *
 */
public class ReflectDemo4 {
	public ReflectDemo4(int a, int b) {}
	public static void main(String[] args) {
		/*String s ="Hello";
		ClassUtil.printClassMethodMessage(s);
		Integer n1 = 1;
		ClassUtil.printClassMethodMessage(n1);*/
		ClassUtil.printConMessage(new ReflectDemo4(1, 2));
		ClassUtil.printConMessage(new Integer(11));
	}
}
