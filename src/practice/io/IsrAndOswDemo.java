package practice.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 练习InputStreamReader和OutputStreamReader的练习，它们相当于字符流的底层
 */
public class IsrAndOswDemo {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		FileInputStream in = null;
		FileOutputStream out = null;
		InputStreamReader isr = null;
		OutputStreamWriter osw = null;
		try {
			in = new FileInputStream("G:/io/test.txt");
			out = new FileOutputStream("G:/io/3.txt");
			isr = new InputStreamReader(in,"gbk"); //不加参数，默认项目的编码
			osw = new OutputStreamWriter(out, "gbk");
			System.out.println(isr.getEncoding());
			
			//一次读一个字符
//			int c;
//			while ((c = isr.read()) != -1) {
//				System.out.print((char)c);
//			}
			
			//一次都一个字符数组
			char[] buffer = new char[8*1024];
			int c;
			//批量读取,放入buffer这个字符数组，从第0个位置开始放置，最多放buffer.length个
			//返回的是读的是字符的个数
			while ((c = isr.read(buffer, 0, buffer.length)) != -1) {
				String s = new String(buffer, 0, c);
				System.out.print(s);
				System.out.println(s.length());
				osw.write(buffer, 0, buffer.length);
			}
			osw.flush();
			osw.close();
			
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (in != null) {
					in.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (out != null) {
					out.close();
				}
//				if (osw != null) {
//					osw.flush();
//					osw.close();
//				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

}
