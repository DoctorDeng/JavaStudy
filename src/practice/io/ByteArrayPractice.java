package practice.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/*
 * 练习数组字节流的使用：注意：数组字节流读写操作不会发生IOException
 */
public class ByteArrayPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int n = -1;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i=1; i<=127; i++) {
			out.write(i);
		}
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		while((n=in.read()) != -1) {
			if (n%2 == 0) {
				System.out.print("\n");
				System.out.printf("\t字节%d,对应字符\'%c\'", n, (char)n);
			}
		}
	}

}
