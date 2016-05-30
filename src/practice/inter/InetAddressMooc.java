package practice.inter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * 
 * @author Doctor邓
 * 慕课网上关于InetAddress的练习
 */
public class InetAddressMooc {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//获取本机的InetAddress实例
		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("计算机名: " + address.getHostName());
			System.out.println("IP地址: " + address.getHostAddress());
			
			byte[] bytes = address.getAddress(); //获取字节数组的IP地址
			System.out.println("字节数组形式的IP: " + Arrays.toString(bytes));
			System.out.println(address);
			
			//根据机器名获取InetAddress实例
			InetAddress address2 = InetAddress.getByName("Doctor邓");
			System.out.println("计算机名: " + address2.getHostName());
			System.out.println("IP地址: " + address2.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
