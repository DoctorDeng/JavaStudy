package practice.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/*
 * 练习ByteArrayOutputStream和 DataOutputStream的使用
 */
public class DataArrayPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//此类实现了一个输出流，其中的数据被写入一个 byte 数组。
		//缓冲区会随着数据的不断写入而自动增长。可使用 toByteArray() 和 toString() 获取数据。 
		ByteArrayOutputStream  baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		ByteArrayInputStream bais = null;
		DataInputStream dis = null;
		try {
			dos.writeDouble(Math.random());
			dos.writeBoolean(true);
			bais = new ByteArrayInputStream(baos.toByteArray());
			dis = new DataInputStream(bais);
			System.out.println(dis.readDouble());
			System.out.println(dis.readBoolean());
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (bais != null) {
					bais.close();
				}
				if (dis != null) {
					dis.close();
				}
				dos.close();
				baos.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

}
