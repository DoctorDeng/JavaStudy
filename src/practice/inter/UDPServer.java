package practice.inter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 
 * @author Doctor邓
 * 练习UDP数据报的使用
 * UDP服务端类，基于UDP的用户登陆
 */
public class UDPServer {
	public static void main(String[] args) {
		try {
			/*
			 * 服务器端接收客户端的数据
			 */
			//1.创建服务器端DatagramSocket，指定端口
			DatagramSocket socket = new DatagramSocket(8800);;
			System.out.println("服务器端，已启动，等待客户端的连接！");
			//2.创建数据报，用于接收客户端发送的数据
			byte[] data = new byte[500]; //指定接收数据报的大小
			DatagramPacket packet = new DatagramPacket(data, data.length);
			System.out.println("***服务器端已经启动，等待客户端发送数据***");
			while (true) {
				//System.out.println(socket);
				socket.receive(packet);//此方法在接收数据报之前会一直阻塞
				Runnable runnable = new UDPServerThread(socket, packet, data);
				Thread client = new Thread(runnable);
				client.start();
			}
			
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}
