package practice.inter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 练习Socket的使用
 * @author Doctor邓
 * 客户端
 */

public class SocketClient {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String s = null;
		Socket mySocket;
		DataInputStream in = null;
		DataOutputStream out = null;
		int i = 1;
		
		try {
			mySocket = new Socket("localhost", 1080);
			//mySocket = new Socket("10.244.0.200", 8899);
			in = new DataInputStream(mySocket.getInputStream());
			//System.out.println("连接成功");
//			while ((s = in.readUTF()) != null) {
//				System.out.println(s);
//			}
			
			out = new DataOutputStream(mySocket.getOutputStream());
		    out.writeInt(i);
			
			while(true) {
				i ++;
				s = in.readUTF();
				out.writeInt(i);
				System.out.println("客户收到: " + s);
				if ( i > 128) {
					break;
				}
				Thread.sleep(500);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

}
