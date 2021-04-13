package practice.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSeraDemo1 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String file = "demo/obj.dat";
		//1、对象的序列化，将对象存到文件中
		ObjectOutputStream oos = null;
		
		//2、对象的反序列化，将对象从文件中读出来
		ObjectInputStream ois = null;
		
		
		try {
			//存对象
			oos = new ObjectOutputStream(new FileOutputStream(file));
			Student stuSave = new Student(1,"Doctor");
			oos.writeObject(stuSave);
			
			//取对象
			ois = new ObjectInputStream(new FileInputStream(file));
			Student stuGet = (Student)ois.readObject();
			System.out.println(stuGet);
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
