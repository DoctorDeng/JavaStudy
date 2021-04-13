package practice.inter;

import java.net.InetAddress;
import java.util.Scanner;

/**
 * 练习InetAddress的使用
 * 
 */
public class InetAddressPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		InetAddress myIp = null;
		
		try {
			//通过InetAddress类的静态方法，返回本地主机对象
			myIp = InetAddress.getLocalHost();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//通过InetAddress类的getHostAddress() 方法获得IP地址字符串
		System.out.println("-------------------本地IP-------------------");
		System.out.println(myIp.getHostName());
		System.out.println(myIp.getHostAddress());
		
		InetAddress searchIp = null;
		Scanner input = new Scanner(System.in);
		System.out.println("请输入要查询的IP地址的域名: ");
		String ipName = input.next();
		try {
			//通过InetAddress类的静态方法，返回指定域名的IP地址对象
			searchIp = InetAddress.getByName(ipName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("-------------------搜索IP-------------------");
		System.out.println("域名: " + ipName + "对应的IP地址为: " + searchIp.getHostAddress());
	}
	

}
