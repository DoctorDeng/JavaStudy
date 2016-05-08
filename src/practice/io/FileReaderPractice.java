package practice.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 练习   FileReader:字符输入流
 *     FileWriter:字符输出流
 */
public class FileReaderPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		File file = null;
		FileWriter out = null;
		FileReader in = null;
		char b[] = "欢迎 hello world".toCharArray();  //把字符串转换成一个新的字符数组
		
		try {
			file = new File("E:/hello.txt");
			out = new FileWriter(file, true);
			out.write(b);
			out.write("来到黄冈示范学院");
			in = new FileReader(file);
			int n = 0;
			while ( (n = in.read(b, 0, 5)) != -1) {
				String str = new String(b, 0, n);
				System.out.println(str);
			}
			
		}
		catch (IOException e) {
			System.out.println(e);
		}
		finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}
	}

}
