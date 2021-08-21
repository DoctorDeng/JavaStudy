package practice.inter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author doctordeng
 * 基于TCP协议的Socket通信，实现用户登录
 * 服务器端
 */
public class ServerMooc {
	public static void main(String[] args) {
		Socket socket = null;
		ServerSocket serverSocket = null;
		int count = 0;
		try {
			//1.创建要给服务器端Socket，即ServerSocket，指定绑定端口，并监听此端口
			serverSocket = new ServerSocket(7777);
			
			//2.调用accept()方法开始监听，等待服务端的连接
			System.out.println("***服务器即将启动，等待客户端的连接***");
			//循环监听,等待客户端的连接
			while(true) {
				//调用accept()方法开始监听，等待客户端的连接
				socket = serverSocket.accept();
				//创建一个新的线程
				ServerThreadMooc serverThread = new ServerThreadMooc(socket); 
				serverThread.start();
				count ++;
				System.out.println("客户端的数量: " + count);
				InetAddress address = socket.getInetAddress();
				System.out.println(address.getHostName());
			}
			//System.out.println("客户端: " + socket.getLocalAddress() + "已连接");
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			
				try {
					if (serverSocket != null) {
						serverSocket.close();
					}
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		}
	}
}
