package practice.inter;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 练习慕课网：URL类的使用。
 * 
 */
public class URLMooc {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try {
			//创建一个URL实例
			URL imooc = new URL("http://www.imooc.com");
			//？ 后面表示参数， # 后面表示锚点
			URL url = new URL(imooc, "/index.html?username=tom#test");
			System.out.println("协议: " + url.getProtocol());
			System.out.println("主机: " + url.getHost());
			//在创建URL对象时，如果没有指定端口号，则使用的是默认的端口号 http使用的是80 。
			//此时getPort()方法返回值为负一
			System.out.println("端口: " + url.getPort()); 
			System.out.println("文件路径: " + url.getPath());
			System.out.println("文件名: " + url.getFile());
			System.out.println("相对路径: " + url.getRef());
			System.out.println("查询字符串: " + url.getQuery());
			
			
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
