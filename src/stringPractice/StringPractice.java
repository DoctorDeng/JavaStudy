package stringPractice;
/*
 * Description:   练习String类的使用 
 */
public class StringPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String s = new String("12343");
		String tom = s;
		System.out.println(tom);
		
		char a[] = {'a','b','c'};
		String s1 = new String(a);
		System.out.println(s1);
		
		char b[] = {'s','t','b','g','t'};
		String s2 = new String(b,1,2);
		System.out.println(s2+"   s2的长度是:"+s2.length());
		System.out.println(s2.contains("sd"));
		
		String s3,s4;
		s3 = "1213dsgfdhgsfdsfdgsfdh";
		s4 = "1sdfhytktuyktuykghfjfhgj";
		System.out.println(s3.startsWith("1213"));
		System.out.println(s4.endsWith("hgj"));
		
		
		

	}

}
