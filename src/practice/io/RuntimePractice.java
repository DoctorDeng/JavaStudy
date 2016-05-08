package practice.io;
/*
 * 联系Runtime类的使用，使用它的exec()方法，打开本地的可执行文件或执行一个操作
 */

import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class RuntimePractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		try{
			Runtime ce = Runtime.getRuntime();
			//打开Windows记事本程序
			File file = new File("C:/Windows", "Notepad.exe");
			ce.exec(file.getAbsolutePath());
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
		
		}
	}

}
