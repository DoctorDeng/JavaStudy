package exceptionPractice;
/*
 * Description:   模拟借书系统
 *         说明:   主要练习Java异常的相关知识：对用户输入错误，抛出各种异常。
 *                共二种异常：
 *                	错误命令异常：输入类型错误
 *                  图书不存在异常：书名不存在或者图书序号超过字符串数组范围。
 *Author：                   Doctor邓
 *Time：                        2016-4-28                  
 */

import java.util.Scanner;

//----------------错误命令异常--------------------//
/*class OrderErrorException extends Exception{
	
	public String getErrorMessage(){
		return "命令错误异常";
	}
}*/

//----------------图书不存在异常-----------------//
/*class BookNotFoundException extends Exception{
	
	public String getBookNotFoundMessage(){
		return "图书不存在";
	}
}*/

//----------------图书信息-----------------------//


//-----------------图书查找类------------------//
/*class BookSearch{
	
	
	
	public String search(int id){
		
		return "";
	}
	
	public String search(String name){
		
		return "";
	}
	
}*/

public class ExceptionHomeWork {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Scanner input = new Scanner(System.in);
		
	}

}
