package practice.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RafDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		File demo = new File("Demo");
		if (!demo.exists()) {
			demo.mkdir();
		}
		else {
			File file = new File(demo,"raf.dat");
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					System.out.println("出现错误!");
				}
			}
			else {
				try {
					RandomAccessFile raf = new RandomAccessFile(file,"rw");
					
					//指针的位置
					System.out.println(raf.getFilePointer());
					raf.write('A');//只写了一个字节
					System.out.println(raf.getFilePointer());
					raf.write('B');
					System.out.println(raf.getFilePointer());
					
//					int i = 0x7fffffff;//int类型占四字节
//					//使用wirte方法每次只能写一个字节，如果要把i写进去，要写4次
//					raf.write(i >>> 24); //高8位
//					raf.write(i >>> 16);
//					raf.write(i >>> 8);
//					raf.write(i);
//					System.out.println(raf.getFilePointer());
					
					//可以直接写一个int
					//raf.writeInt(1);
					
					//写一个汉字
					String s = "中";
					byte[] utf = s.getBytes("UTF-8");
					raf.write(utf);
					System.out.println(raf.length());
					
					//读文件，必须把指针移到头部
					raf.seek(0);
					//一次性读取 ，把文件中的内容，都读到字节数组中    //length()方法返回的是一个lang型
					byte[] buf = new byte[(int)raf.length()];  
					raf.read(buf);
					System.out.println(Arrays.toString(buf));
					for (byte temp : buf) {
						System.out.print(Integer.toHexString(temp & 0xff));
					}
					System.out.println();
					String s1 = new String(buf);
					System.out.println(s1);
					
					raf.close();
				}
				catch (IOException e){
					System.out.println("出错误了");
				}
			}
		}
	}

}
