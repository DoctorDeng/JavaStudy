package practice.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 练习BufferedInputStream 和 BufferedOutputStream（缓冲流）的使用
 */
public class BufferStream {

	//使用缓冲流来进行文件的复制
	public static void copyFileBuffer(File srcFile, File targetFile) throws IOException {
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("文件: " + srcFile +"不存在");
		}
		if (!targetFile.isFile()) {
			throw new IllegalArgumentException(targetFile +  "不是文件!");
		}
		
		BufferedInputStream buffIn = new BufferedInputStream(new FileInputStream(srcFile));
		BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(targetFile));
		
		int c;
		while ((c = buffIn.read()) != -1) {
			buffOut.write(c);
		}
		buffOut.flush();
		buffIn.close();
		buffOut.close();
	} 
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try {
			File file1 = new File("G:/io/4级《学霸秘籍》.pdf");
			File file2 = new File("G:test.pdf");
			if (file2.isFile()) {
				System.out.println("111");
			}
			BufferStream.copyFileBuffer(file1, file2);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
