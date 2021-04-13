package practice.inter;

/**
 * 练习Socket的使用
 * @author Doctor邓
 * 服务器端
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ServerSocket server = null;
		Socket you = null;
		DataOutputStream out = null;
		DataInputStream in = null;
		
		try {
			server = new ServerSocket(1080);
		} catch (IOException e) {
			System.out.println("Error:" + e);
		}
		
		try {
			you = server.accept();
			System.out.println("客户端: " + you.getInetAddress().getHostName() + "已连接");
			in = new DataInputStream(you.getInputStream());
			out = new DataOutputStream(you.getOutputStream());
			
			while(true) {
				int m = 0;
				m = in.readInt();
				out.writeUTF("你说的数字字符时: " + (char)m);
				System.out.println("服务器收到: " + m);
				Thread.sleep(500);
			}
		} catch (IOException e) {
			System.out.println("" + e);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
