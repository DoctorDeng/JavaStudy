package practice.io;
/*
 * 练习OutputStreamWriter的使用
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
