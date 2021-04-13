package practice;
/*
 * Description:  值传递和引用传递练习
 * Author:       Doctor邓
 * Time:         2016-4-9
 */
public class Transmit {
	
	//定义方法用途为：交换数组中的元素x[0]和x[1]
    public static void exchangeArray(int[] x){
    	int temp=x[0];
    	x[0]=x[1];
    	x[1]=temp;
    }
    
    public static void exchangeString(String x,String y){
    	String temp = x;
    	x=y;
    	y=temp;
    }
	
	public static void main(String[] args) {
		
		//验证引用传递
		int[] i={3,4};
		System.out.println("交换前i[0]的值:"+i[0]);
		System.out.println("交换前i[1]的值:"+i[1]);
		Transmit.exchangeArray(i);
		System.out.println("交换后i[0]的值:"+i[0]);
		System.out.println("交换后i[1]的值:"+i[1]);
		
		//验证String的传递类型
		String a=new String("aaa");
		String b="bbb";
		System.out.println("交换前a的值:"+a);
		System.out.println("交换前b的值:"+b);
		Transmit.exchangeString(a,b);
		System.out.println("交换后a的值:"+a);
		System.out.println("交换后b的值:"+b);
	}

}