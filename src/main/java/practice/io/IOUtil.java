package practice.io;

import java.io.FileInputStream;
import java.io.IOException;

/*
 * 练习字节流
 */
public class IOUtil {

	/*
	 * 读取指定文件内容，按照16进制输出到控制台
	 * 并且每输出10个byte换行
	 */
	public static void printHex(String fileName) throws IOException{
		FileInputStream file = new FileInputStream(fileName);
		int b;
		int i = 1;
		while ((b = file.read()) != -1) {
			if(b <= 0xf) {
				//单位数前面补0
				System.out.print(0);
			}
			
			System.out.print(Integer.toHexString(b) + " ");
			if (i++ %10 == 0) {
				System.out.println();
			}
		}
		file.close();
	}
	
	public static void printHexByte(String fileName) throws IOException {
		FileInputStream file = new FileInputStream(fileName);
		byte[] bytes = new byte[50*1024];
		//从file批量读取字节，放入到bytes这个字节数组中。从第0个位置开始放，最多放bytes.length个。
		int i = file.read(bytes, 0, bytes.length);
		int j=1;
		System.out.println();
		for (int a=0; a<i;a++) {
			//byte[a]比较的时候是int类型的因此做如下操作
			if ((bytes[a] & 0xff)<= 0xf) {
				//System.out.println(bytes[a]);
				System.out.print(0);
			}
			
			System.out.print(Integer.toHexString(bytes[a] & 0xff) + " ");
			
			if (j++ % 10 == 0) {
				System.out.println();
			}
		}
		file.close();
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try {
			IOUtil.printHex("G:/io/1.txt");
			IOUtil.printHexByte("G:/io/1.txt");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		try {
			long start = System.currentTimeMillis();
			IOUtil.printHexByte("G:/io/4级《学霸秘籍》.pdf");
			long end = System.currentTimeMillis();
			System.out.println();
			System.out.println(end - start);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
