package practice.io;

import java.io.File;
import java.io.IOException;

/*
 * 练习File类的常用操作：
 *    过滤、遍历等操作。
 */
public class FileUtils {

	//列出指定目录（包括其子目录）的所有文件
	public static void listDirectory(File dir) throws IOException{
		if (!dir.exists()) {
			
			//IllegalArgumentException:不合法的参数异常
			throw new IllegalArgumentException("目录：" + dir + "不存在.");
		}
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException(dir + "不是目录");
		}
		String[] filenames = dir.list(); //返回的不包括子目录里面的文件或目录的信息
		for (String temp : filenames) {
			System.out.println(temp);
		}
		//如果要遍历子目录下的内容就需要构造File对象做递归操作。File提供了直接返回File对象的API
		File[] files = dir.listFiles(); //返回直接子目录（文件）的抽象
		if (files!=null && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory()) {
					listDirectory(file);
				}
				else {
					System.out.println(file);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		File file = new File("G:/Xiaomi/MiPhoneManager");
		try {
			FileUtils.listDirectory(file);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
