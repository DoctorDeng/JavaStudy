package practice.io;
/*
 * 练习OutputStreamWriterPractice的使用
 */
import java.io.*;
public class OutputStreamWriterPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try {
			OutputStreamWriter wite = new OutputStreamWriter(new FileOutputStream("E:test.txt"));
			wite.write("mircosoftDoctorDeng");
			System.out.println(wite.getEncoding());
			wite.close();
			wite = new OutputStreamWriter(new FileOutputStream("E:test.txt",true), "GBK");
			System.out.println(wite.getEncoding());
			wite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
