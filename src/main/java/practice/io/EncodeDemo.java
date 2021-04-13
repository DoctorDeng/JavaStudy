package practice.io;

import java.io.UnsupportedEncodingException;

public class EncodeDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String s = "慕课ABC";
		
		//getBytes()将字符串转换为字节序列，默认使用项目默认的编码
		byte[] bytes1 = s.getBytes(); 
		for (byte b : bytes1) {
			//Integer.toHexString():
			//以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
			System.out.print(Integer.toHexString(b & 0xff) + " ");
			/* 
			 * 这里将byte b 与 0xff(十进制位256,本身是十六进制，即1111 1111) 进行 &(与)运算
			 * 而不是直接将b转换为16进制，是因为：
			 *    byte类型只有八位，而Integer.toHexString()的参数是int类型的
			 *    这样要将byte类型转换为32位的int类型，这样在转入的时候会byte会进行补位(高位补1)，这样会
			 *    出现数据丢失，所以与0xff进行与运算后，多出来的24位会清0而不是变为1。这样结果就对了。
			 * */
		}
		System.out.println();
		try {
			byte[] bytes2 = s.getBytes("gbk"); //getBytes():也可以指定编码方式将字符串转换为数组,不过要使用try{}catch{}语句
			for (byte b : bytes2) {
				System.out.print(Integer.toHexString(b & 0xff) + " ");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println();
		//java:utf-16be编码
		try {
			byte[] bytes3 = s.getBytes("utf-16be");
			for(byte b : bytes3) {
				System.out.print(Integer.toHexString(b & 0xff) + " ");
			}
			System.out.println();
			String str1 = new String(bytes3); //用项目默认的编码
			System.out.println(str1);
			String str2 = new String(bytes3, "utf-16be");
			System.out.println(str2);
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}

}
