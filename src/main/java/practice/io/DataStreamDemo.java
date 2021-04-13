package practice.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 练习DataInputStream和DataOutputStream的使用
 */
public class DataStreamDemo {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		String  file = "bin/foundationEnhancement/classLoaderTest/ClassLoaderAttachment.java";
		DataOutputStream test = new DataOutputStream(new FileOutputStream(file));
		int a = 100;
		double b = 100.0;
		long c = 100l;
		
		test.writeDouble(b);
		test.writeLong(c);
		test.writeInt(a);
		test.writeUTF("123165中国");   //采用UTF-8编码写出
		test.writeChars("中国"); //采用UTF-16be写出
		
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		System.out.println(in.readDouble());
		System.out.println(in.readLong());
		System.out.println(in.readInt());
		System.out.println(in.readUTF());
		System.out.println(in.readChar());
		in.close();
 	}
	
	

}
