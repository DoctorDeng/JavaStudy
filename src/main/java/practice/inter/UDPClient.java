package practice.inter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 
 * @author Doctor邓
 * 客户端
 */
public class UDPClient {
	public static void main(String[] args) {
		try {
			//1.定义服务器的地址、端口号、数据
			InetAddress address = InetAddress.getByName("localhost");
			int port = 8800;
			byte[] data = "用户名: admin; 密码: 123".getBytes();
			//2.创建数据包，包含发送的数据信息
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			//3.创建数据包，包含发送的数据信息
			DatagramSocket socket = new DatagramSocket();
			//4.向服务器端发送数据报
			socket.send(packet);
			
			/*
			 * 客户端接收服务器端响应信息
			 */
			//1.创建数据报，用于接收服务器端相应的数据
			byte[] data2 = new byte[1024];
			DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
			//2.接收服务器端响应的数据
			socket.receive(packet2);
			//3.读取数据
			String reply = new String(data2, 0, packet2.getLength());
			System.out.println("我是客户端,服务器说: " + reply);
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
