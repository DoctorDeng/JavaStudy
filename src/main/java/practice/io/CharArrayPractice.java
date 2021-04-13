package practice.io;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

/*
 * 练习数组字符流: 注意：与数组字节流不同的是数组字符流可能发生IOException
 */
public class CharArrayPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int n = -1;
		CharArrayWriter out = new CharArrayWriter();
		for (int i=20320; i<=20520; i++) {
			out.write(i);
		}
		CharArrayReader in = new CharArrayReader(out.toCharArray());
		
		try {
			while ((n=in.read()) != -1) {
				if (n%2==0) {
					System.out.printf("\n");
				}
				System.out.printf("\t位置%d,字符\'%c'", n, (char)n);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
