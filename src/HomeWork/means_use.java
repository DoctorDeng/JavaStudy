package HomeWork;
import java.util.Arrays;
import java.util.Scanner;
public class means_use {
	public int[] getArray(int length){
		int[] num=new int[length];
		for(int i=0;i<num.length;i++){
			num[i]=(int)(Math.random()*100);
		}
		return num;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Scanner input = new Scanner(System.in);
		System.out.println("请输入数组的长度(长度小于100哦！)");
		int arrayLength=input.nextInt();
	    means_use hello=new means_use();
	    int[] getNum=hello.getArray(arrayLength);
	    System.out.println(Arrays.toString(getNum));
	}
}
