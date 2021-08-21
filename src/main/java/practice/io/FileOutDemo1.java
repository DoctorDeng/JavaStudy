package practice.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 使用FileInputStream 和 FileOutputStream 实现文件的复制
 */
public class FileOutDemo1 {

	public static void copyFile(File srcFile, File destFile) throws IOException {
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("文件"+srcFile+"不存在");
		}
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "不是文件");
		}
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		
		byte[] buf = new byte[8*1024];
		int b;
		while ((b = in.read(buf, 0, buf.length)) != -1) {
			out.write(buf, 0, b);
			out.flush();
		}
		in.close();
		out.close();
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
//		FileOutputStream out = null;
//		try {
//			//如果文件不存在，则直接创建，如果存在，删除后创建
//			out = new FileOutputStream("demo/test.txt");
//			out.write('A'); //写出了A的低八位
//			out.write('B'); //写出了B的低八位
//			int a = 10; //write只能写八位,那么写一个int需要写四次，每次8位
//			out.write(a >>> 24);
//			out.write(a >>> 16);
//			out.write(a >>> 8);
//			out.write(a);
//			byte[] utf = "doctordeng".getBytes("UTF-8");
//			out.write(utf);
//			IOUtil.printHex("demo/test.txt");
//			
//			
//		} catch (FileNotFoundException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		finally{
//			if(out != null) {
//				try {
//					out.close();
//				} catch (IOException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}
//			}
//		}
		
		File file1 = new File("G:/io/4级《学霸秘籍》.pdf");
		File file2 = new File("G:test.pdf");
		
		
		
		try {
			if (!file2.exists()) {
				file2.createNewFile();
			}
			copyFile(file1, file2);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
