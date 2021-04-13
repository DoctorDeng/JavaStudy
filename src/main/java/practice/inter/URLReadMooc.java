package practice.inter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author Doctor邓
 * 使用URL读取网页中的内容
 */
public class URLReadMooc {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try {
			//创建一个URL实例
			URL url = new URL("http://www.baidu.com");
			//通过URL的openStream()方法获取URL对象所表示的资源的字节输入流
			InputStream in = url.openStream();
			//将字节输入流转换为字符输入流
			//注意网站用的HTML的编码方式，例如这里百度的为UTF-8编码，这里也应该改为UTF-8编码
			InputStreamReader inReader = new InputStreamReader(in);
			//为字符流添加缓冲
			BufferedReader bReader = new BufferedReader(inReader);
			
			String data = bReader.readLine();  //读取数据
			while ( data != null ) {
				System.out.println(data); //输出数据
				data = bReader.readLine();
			}
			bReader.close();
			inReader.close();
			bReader.close();
			
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
