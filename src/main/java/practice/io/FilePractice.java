package practice.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/*
 * 联系File类的使用
 */
public class FilePractice {

	public static void main(String[] args) {  
		// TODO 自动生成的方法存根
		
//		System.out.println("文件系统目录");
//		for (File root : File.listRoots()) {
//			System.out.format("%s",	root);
//		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
//		
//		System.out.println();
//		try {
//			showFile();
//		} catch (IOException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
		FilePractice  file = new FilePractice();
		try {
			file.listFile();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

	public static void showFile() throws IOException {
		File f = new File("C:\\test\\test.txt");
		File f1 = new File("C:\\test\\test1.txt");
		
		f1.createNewFile();
		System.out.format("输出字符串: %s%n", f);
		System.out.format("判断File类对象是否存在: %b%n", f.exists());
		
		System.out.format( "获取File类对象最后修改时间: %tc%n", f.lastModified());
		System.out.format( "判断File类对象是否是文件: %b%n", f.isFile());
		System.out.format( "判断File类对象是否是目录: %b%n", f.isDirectory());
		System.out.format( "判断File类对象是否有隐藏属性: %b%n", f.isHidden());
		System.out.format( "判断File类对象是否可读: %b%n", f.canRead());
		System.out.format( "判断File类对象是否可写: %b%n", f.canWrite());
		System.out.format( "判断File类对象是否可执行: %b%n", f.canExecute());
		System.out.format( "判断File类对象是否是绝对路径: %b%n", f.isAbsolute());
		System.out.format( "获取File类对象的长度: %d%n", f.length());
		System.out.format( "获取File类对象的名称: %s%n", f.getName());
		System.out.format( "获取File类对象的路径: %s%n", f.getPath());
		System.out.format( "获取File类对象的据对路径: %s%n", f.getAbsolutePath());
		System.out.format( "获取File类对象父目录的路径: %s%n", f.getParent());
	 	
	}
	
	
	//联系使用list()方法:列出当File对象为目录时，列出该目录下的文件和子目录。
	public void listFile() throws IOException{
		File f = new File("C:\\test\\com");
		f.createNewFile();
		
		FileAccept accpt = new FileAccept("txt");
		File fileName[] = f.listFiles(accpt);
		for (File temp : fileName) {
			System.out.printf("文件名称：%s, 长度：%d", temp.getName(), temp.length());
		}
		
	}
}

//filter： 过滤器
//在使用list(FilenameFilter obj)是必须有实现了FilenameFilter接口的类
class FileAccept implements FilenameFilter {
	
	String str = null;
	FileAccept(String s) {
		str = "." + s;
	}
	
	//判断目录下是否有此类型文件
	public boolean accept(File dir, String name) {
		return name.endsWith(str);
	}
}
