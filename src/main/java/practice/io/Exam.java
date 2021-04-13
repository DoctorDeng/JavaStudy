package practice.io;
/*
 * 练习BufferedReader 和 BufferedWriter 的使用，
 * 说明：使用输入流读取试题文件，每次显示一道题目。
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exam {
	
    private InputStreamReader input = null;  //获取键盘输入
    private BufferedReader read = null;      //读取试卷
    private BufferedReader readInput = null;  //获取用户输入
    private StringBuffer answer;             //存放用户答案
    private int score;                       //存放用户分数
    private final String[] RESULTS = {"A", "B", "C", "D"};
    
	public Exam() {
		answer = new StringBuffer();
		score = 0;
		input = new InputStreamReader(System.in);
		readInput = new BufferedReader(input);
	}
	
	//计算分数
	public int countScore(StringBuffer answer) {
		for (int i =0; i< answer.length(); i++) {
			if (RESULTS[i].equals(String.valueOf(answer.charAt(i)))) {
				score = score + 25;
			}
		}
		return score;
	}
	
	//界面
	public void menu() throws FileNotFoundException, IOException{
		String str = null;
		String temp = null;
		String regex = "[A-D]";  //规定答案只能为A,B,C,D
		
		read = new BufferedReader(new FileReader("Demo/testItem.txt"));
		
		while( (str = read.readLine()) != null) {
			if (!str.contains("*")) {
				System.out.println(str);
			}
			else {
				while(true) {
					System.out.print("请输入你的答案(A,B,C,D):");
					temp = readInput.readLine();
					if (temp.matches(regex)) {
						answer.append(temp);
						//System.out.println(answer);
						break;
					}
					else {
						System.out.println("输入的答案不符合类型,请重新输入！");
						continue;
					}
				}
			}
		}
		System.out.println("您的分数为:" + countScore(answer));
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Exam exam = new Exam();
		try {
			exam.menu();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}

}
