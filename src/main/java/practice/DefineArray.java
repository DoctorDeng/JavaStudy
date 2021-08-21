package practice;
/*
 * Description:    练习定义数组的几种方法
 * Author:         doctordeng
 * Time:           2016-4-9
 */
public class DefineArray {
	
	//定义数组的几种方法
	public static void arrayDefine(){
		
		//第一种方法
		int[] a=new int[5];
		System.out.println("这是第一种方法定义的数组,它的长度为: "+a.length);
		
		//第二种方法
		int[] b=new int[]{1,2,2,3,4,4,4,4,4,4,44,4,};
		System.out.println("这是第二种方法定义的数组,它的长度为: "+b.length);
		
		//第三种方法
		int[] c;
		c=new int[3];
		System.out.println("这是第三种方法定义的数组,它的长度为: "+c.length);
		
		//第四种方法
		int[] d={1,1,1,1,1,1,1};
		System.out.println("这是第四种方法定义的数组,它的长度为: "+d.length);
	}

	
	public static void main(String[] args) {
		DefineArray.arrayDefine();

	}

}