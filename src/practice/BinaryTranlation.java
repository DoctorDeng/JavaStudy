package practice;
import java.util.Scanner;
/* 
 * Description:   实现十进制转二进制
 * Author:        Doctor邓
 * Time:          2016-4-9
 */
public class BinaryTranlation {

	//实现十进制数转转换为二进制
	public static void binaryTranlationLoop(int n){
		
		int[] result =new int[32]; //用于存储转换为二进制的倒序结果，长度为32，符合int类型数据的范围		
		int residue;               //用于存储每次n除以2后的余数
		int a=0;                   //用于记住数组实际使用的长度
		
		//循环方法：将转换为二进制的结果倒序存储在数组中
		for(int i=0;n>0;i++){
			
			residue=n%2;
			if(residue==0){
				result[i]=0;
			}
			else{
				result[i]=1;
			}
			a++;
			n=n/2;
		}
		
		//输出转换为二进制的结果
		for(int i=a-1;i>=0;i--){
			System.out.print(result[i]);
		}
		System.out.println();
	}
	
	
    //递归方法：将转换为二进制的结果倒序存储在数组中
	public static void binaryTranlationRecursion(int n){
		
	    if(n==0) return;
	    binaryTranlationLoop(n/2);
	    System.out.print(n%2);
	}
	
	public static void main(String[] args) {
		
		//验证循环方法进制转换的正确性
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个正整数");
		int n=input.nextInt();
		System.out.println("这是循环方法的结果: ");
		BinaryTranlation.binaryTranlationLoop(n);
		
		//验证递归方法进制转换的正确性
		System.out.println("这是递归方法的结果: ");
		BinaryTranlation.binaryTranlationRecursion(n);
	}
}
