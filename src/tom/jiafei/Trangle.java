package tom.jiafei;
/*
 * Description:   练习package语句和import语句的使用
 * Function:      判断三个数，能否构成三角形，如果可以求出其面积
 * Author:        Doctor邓
 * Time:          2016-4-18
 */

public class Trangle {
	double sideA, sideB, sideC;
    boolean boo;       //用于判断是否计算三角形面积
   
    public Trangle(double a, double b, double c){
    	sideA = a;
    	sideB = b;
    	sideC = c;
    	
    	//判断三个数能否构成三角形
    	if(a+b > c && a+c > b && b+c > a){
    		System.out.println("我是一个三角形");
    		boo = true;
    	}
    	else{
    		System.out.println("我不是一个三角形");
    		boo = false;
    	}
    }
    
    //计算三角形面积
    public void getArea() {
    	
    	if(boo == true){
    		double p = (sideA + sideB + sideC)/2.0;
        	double area = Math.sqrt(p*(p - sideA)*(p - sideB)*(p - sideC));
        	System.out.println("是一个三角形,能计算面积");
            System.out.println("面积是: "+area); 
    	}
    	else{
    		System.out.println("不是一个三角形,不能计算面积");
    	}
    }
    
    public void changeSide(double a, double b, double c){
    	sideA = a;
    	sideB = b;
    	sideC = c;
    	
    	if(a+b > c && a+c > b && b+c > a){
    		boo = true;
    	}
    	else{
    		boo = false;
    	}
    }
    
}
