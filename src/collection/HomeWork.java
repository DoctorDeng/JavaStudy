package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * 慕课网 作业
 * 1、创建完List<String>之后，往其中添加十条随机字符串
 * 2、每天字符串长度为10以内的随机整数
 * 3、每条字符串的每个字符随机生成，且可重复
 * 3、字符串不可重复
 */
public class HomeWork {
	
	List<String> listString = new ArrayList<String>();

	//随机生成字符串方法
	public String createString() {
		
		int stringLength;       //字符串长度
		String string = "";          //所要生成的字符串
		char[] chars;           
		Random random = new Random();
		
		//ASSIC码表示字符串的范围
		int max = 103;
		int min = 32;
		
		do{
			stringLength = random.nextInt(10);
			chars = new char[stringLength];  
		}while (stringLength == 0);
		
		for (int i=0; i<stringLength; i++) {
			chars[i] = (char) (random.nextInt(max)%(max - min + 1) + min);
			//System.out.println("元素为:" + chars[i]);
		}
		
		for(int i=0; i<chars.length; i++) {
			string = string + String.valueOf(chars[i]);
		}
		return string;
	}
	
	//对字符串进行排序
	public void sortString() {
		
		//将十条字符串添加到List<string>中
		for (int i=0; i<10; i++) {
			String temp = createString();
			listString.add(temp);
			System.out.println("成功添加字符串: " + temp);
			
		}
		
		System.out.println("-------------排序前----------");
		for(String string : listString) {		
			System.out.println("元素: " + string);
		}
		
		Collections.sort(listString);
		
		System.out.println("-------------排序后----------");
		for(String string : listString) {
			System.out.println("元素: " + string);
		}
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		HomeWork work =  new HomeWork();
		work.createString();
		work.sortString();
	}

}
