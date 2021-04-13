package practice.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 练习BufferedReader缓冲输入流
 *    BufferedWriter缓冲输出流
 */
public class BufferedWriterPractice {
	Scanner input = new Scanner(System.in);
	List<Student> stuList;  //用于存储Student信息
	//存Student信息
	public void saveInfor(int stuId, String stuName) {
		FileWriter writer = null;
		BufferedWriter buWriter = null;
		
		try {
			writer = new FileWriter("E:/student.txt", true);
			buWriter = new BufferedWriter(writer);
			buWriter.write(stuId+",");
			buWriter.write(stuName);
			buWriter.newLine();
		}
		catch (IOException io) {
			io.printStackTrace();
		}
		finally {
			try {
				if (buWriter != null) {
					buWriter.flush();
					buWriter.close();
				}
				if (writer != null) {
					writer.close();
				}
				
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	//取Student的信息
	@SuppressWarnings({ "finally" })
	public List<Student> getInfor() {
	
		Student student;
		stuList = new ArrayList<Student>();  //用于存储Student的信息
		FileReader reader = null;
		BufferedReader buReader = null;
		
		try {
			
			reader = new FileReader("E:/student.txt");
			buReader = new BufferedReader(reader);
			String temp = null;          //存储每次取得的文件的一行数据
			String[] stu;                //存取被分割后的temp数据
			
			while( (temp = buReader.readLine()) != null ) {
				stu = temp.split(",");
				student = new Student( Integer.parseInt(stu[0]), stu[1]);
				stuList.add(student);
			}
			
		}
		catch (FileNotFoundException file) {
			System.out.println("未发现存储文件 ：student.txt");
		}
		catch (IOException io) {
			System.out.println("读取文件信息时出现错误");
		}
		finally {
			if(reader != null) {
				try {
					reader.close();
					buReader.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			return stuList;
		}
	}
	
	//显示Student信息
	public void  showInfor() {
		List<Student> stuList = getInfor();
		
		for(Student stu : stuList) {
			System.out.println("ID： " + stu.getStuId() + " 姓名: " + stu.getName());
		}
	}
	
	//获取用户输入信息方法
	public void getInput() {
		
		int stuId;
		String stuName;
		
		while (true) {
			System.out.println("请输入学生ID: ");
			if (input.hasNextInt()) {
				stuId = input.nextInt();
				break;
			}
			else { continue; }
		}
		while (true) {
			System.out.println("请输入学生姓名: ");
			if (input.hasNext()) {
				stuName = input.next();
				//System.out.println(stuName);
				break;
			}
			else { continue; }
		}
		
		saveInfor(stuId, stuName);
		System.out.println("信息存储完毕!输入 1 ：显示student信息，其他继续输入！");
		if (input.nextInt() == 1){
			showInfor();
			input.close();
		}
		else {
			getInput();
		}		
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		BufferedWriterPractice test = new BufferedWriterPractice();
		test.getInput();
	}

}
