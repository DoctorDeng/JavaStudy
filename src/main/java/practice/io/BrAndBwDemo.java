package practice.io;
/*
 * 练习带缓冲的BufferedReader和BufferedWriter的使用
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
public class BrAndBwDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		BufferedReader br = null;
//		BufferedWriter bw = null;
		PrintWriter pw = null;
		
		try {
			br = new BufferedReader(
					new InputStreamReader(
						    new FileInputStream("G:io/test.txt"), "gbk"));
//			bw = new BufferedWriter(
//					new OutputStreamWriter(
//							new FileOutputStream("G:io/test1.txt"), "gbk"));
		
			pw = new PrintWriter("G:io/test1.txt");
			//pw = new PrinWriter(outputStream, boolean autoFlush);
			// boolean atuoFlush:自动刷新flush，即可以不用fluse
			
		String line;
		while ((line = br.readLine()) != null){
			System.out.println(line);  //一次读一行，并不能识别换行符
//			bw.write(line);
//			bw.newLine(); //单独写出换行操作
			
			pw.println(line);  //相当于写入一行并换行
			
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (br != null) {
					br.close();
				}
//				if (bw != null) {
//					bw.flush();
//					bw.close();
//				}
				if (pw != null) {
//					pw.flush();
					pw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
