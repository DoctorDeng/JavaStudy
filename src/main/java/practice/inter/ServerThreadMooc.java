package practice.inter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * @author doctordeng
 * 服务器端线程处理类
 */
public class ServerThreadMooc extends Thread {
	//和本线程相关的Socket
	Socket socket = null;
	
	public ServerThreadMooc(Socket socket) {
		this.socket = socket;
	}
	
	//线程执行的操作，响应客户端的请求
	public void run() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try {
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr); //为输入流添加缓冲
			String info;
			while ((info = br.readLine()) != null) {
				System.out.println("我是服务器,客户端说: " + info);
			}
			socket.shutdownInput();
			
			//获取输入流，响应客户端的请求
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("你好\r\n");
			pw.flush(); //调用flush()方法将缓冲输出
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			//5.关闭资源
			try {
				if (pw != null) {
					pw.close();
				}
				if (os != null) {
					os.close();
				}
				if (br != null) {
					br.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (is != null) {
					is.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		
		}
		
	}
	
}
