package practice.inter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author doctordeng
 * 客户端
 */
public class ClientMooc {
	public static void main(String[] args) {
		try {
			//1、创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket("localhost", 7777);
			
			//2.获取输出流，向服务器端发送信息
			OutputStream os = socket.getOutputStream(); //字节输出流
			PrintWriter pw = new PrintWriter(os); //将输出流包装为打印流
			pw.write("用户名: admin; 密码: 123");
			pw.flush();
			socket.shutdownOutput(); //注意：流用完后要关闭，不然用不了其他的流。
			
			//3.获取输入流，并读取服务器端的相应信息
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("我是客户端,服务器说: " + info);
			}
			
			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
			//4.关闭资源
			br.close();
			is.close();
			
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
	}
}
