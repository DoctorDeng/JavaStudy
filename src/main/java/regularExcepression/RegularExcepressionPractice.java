package regularExcepression;
/*
 * Description:  练习正则表达式的使用
 *         说明:  程序判断用户从键盘输入的字符序列是否全部由英文字母组成
 * Author:       doctordeng
 * Time:         2016-4-19
 */
import java.util.Scanner;

public class RegularExcepressionPractice {

	public static void main(String[] args) {
 		// TODO 自动生成的方法存根
        String regex = "[a-z,A-Z]+";
        Scanner input = new  Scanner(System.in);
        System.out.println("请输入一行文本!(输入#程序结束):");
        String str = input.nextLine();
        
        while(str != null){
        	if(str.matches(regex)){
        		System.out.println(str+"中的字符都是英文字母");
        	}
        	else{
        		System.out.println(str+"含有非英文字母");
        	}
        	
        	System.out.println("输入一行文本(输入#结束程序):");
        	str = input.nextLine();
        	if(str.startsWith("#")){
        		System.exit(0);
        	}
        }
	}

}
