package practice.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 练习FileReader 和 FileWriter的使用
 */
public class FrAndFwDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader("G:/io/1.txt");
			fw = new FileWriter("G:/io/5.txt", true);
			
			char[] buffer = new char[1024];
			int c;
			while ((c = fr.read(buffer, 0, buffer.length)) != -1) {
				fw.write(buffer, 0, c);
			}
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			
			try {
				if (fr != null) {
					fr.close();
				}
				if (fw != null) {
					fw.flush();
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
