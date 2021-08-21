package practice;
import java.util.Scanner;
/* 
 * Description:    练习递归并用循环实现递归
 * Author:         doctordeng
 * Time:           2016-4-9
 */
public class Recursion {
	
	//递归方法：实现n+(n-1)+(n-2).......+1
    public static int returnSumRecursion(int n){
    	if(n==1) return 1;
    	int sum=returnSumRecursion(n-1);
    	return sum+n;
    }
    
    //循环方法：实现n+(n-1)+(n-2).......+1
    public static int returnSumLoop(int n){
    	int sum=0;
    	for( ;n>=1;n--){
    		sum=sum+n;
    	}
    	return sum;
    }
	
	public static void main(String[] args) {
		
		//验证递归方法是否正确
		Scanner input=new Scanner(System.in);
		System.out.println("请输入一个整数: ");
		int n=input.nextInt();
		int sum=Recursion.returnSumRecursion(n);
		System.out.println("递归方法的结果: "+sum);
		
		//验证循环方法是否正确
		int result=Recursion.returnSumLoop(n);
		System.out.println("循环方法的结果: "+result);
		input.close();
	}

}