package string;
/*
 * Description:  练习StringTokenizer类的使用
 *         说明:  StringTokenizer类将指定字符串通过指定字符进行分割
 */
//--------------程序功能：将一个浮点数的整数部分和小数部分割开------------//

import java.util.Scanner;
import java.util.StringTokenizer;

public class StringTokenizerPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        String []mess={"整数部分","小数部分"};
        System.out.println("请输入一个double类型的数");
        try{
        	 Scanner reader = new Scanner(System.in);
        	 double x = reader.nextDouble();
             String s = String.valueOf(x);
             StringTokenizer fenxi = new StringTokenizer(s,".");
             for(int i = 0;fenxi.hasMoreTokens(); i++){
             	String str = fenxi.nextToken();
             	System.out.println(mess[i]+":"+str);
             }
        }
        catch (Exception e){
        	System.out.println("请输入一个浮点数！");
        }
        
	}

}
