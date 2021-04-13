package practice.genericParadigm;

import java.io.File;
import java.io.IOException;

/*
 * 练习File类的基本使用
 */
public class FileDemo {

	public static void main(String[] args) {
		// 了解构造函数的情况
		File file = new File("G:/io/javaPractice");
		if (!file.exists()) {
			file.mkdirs(); //file.mkdirs()：创建多级目录
		}
		else {
			file.delete();
		}
		//是否是一个目录,如果是目录返回true，如果不是或不存在返回false
		System.out.println(file.isDirectory());
		//是否是一个文件
		System.out.println(file.isFile());
		
		//File file2 = new File("G:/io/1.txt");
		File file2 = new File("G:/io", "1.txt");
		if(!file2.exists()) {
			try {
				file2.createNewFile();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			file2.delete();
			System.out.println(file);
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getName());
			System.out.println(file2.getName());
			System.out.println(file.getParentFile());
			System.out.println(file2.getParentFile());
		}
	}

}
