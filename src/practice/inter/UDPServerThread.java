package practice.inter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 使用多线程，通过UDP，来进行多用户的登陆问题
 * 
 */
public class UDPServerThread implements Runnable {
	DatagramSocket socket = null;
	DatagramPacket packet = null;
	byte[] data;
	
	public UDPServerThread(DatagramSocket socket, DatagramPacket packet, byte[] data) {
		this.socket = socket;
		this.data = data; //数据报大小
		this.packet = packet;
	}
	
	public void run() {
		try {
			String info = new String(data, 0, packet.getLength());
			System.out.println("我是服务器,客户端说: " + info);
			
			/*
			 * 向客户端响应数据
			 */
			//1.定义响应客户端的地址、端口号、数据
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			byte[] data2 = "欢迎您!".getBytes();
			//2.创建数据报,包含响应的数据信息
			DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port );
			//3.响应客户端
			socket.send(packet2);
			
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			if ( socket != null) {
				socket.close();
			}
		}
	}
}
