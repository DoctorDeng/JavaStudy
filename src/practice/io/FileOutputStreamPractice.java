package practice.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 练习FileOutputStream：字节输入流的使用
 *    FileInputStream:字节输出流的使用
 */
public class FileOutputStreamPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		File file = new File("E:/hello.txt");
		byte[] b = "欢迎welcome".getBytes();     
		
		/* getBytes():获取到字符串以指定编码方式编码后所得到的字节数组.
	     * 然后字节数组的长度就是该字符串在指定编码方式下所占的字节数.
         * 注意：String类的不带参数的getBytes（）方法会以程序所运行平台的默认编码方式为准来进行转换.
         * 在不同平台下就会有不同的结果，因此建议使用指定编码方式的getBytes（String charsetName）方法.
         */
		
		try {
			FileOutputStream out = new FileOutputStream(file);
			out.write(b);
			out.close();
			FileInputStream in = new FileInputStream(file);
			int n = 0;
			while( (n = in.read(b, 0, 3)) != -1 ) {
				String str = new String(b, 0, n);
				System.out.println(str);
			} 
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}

}
