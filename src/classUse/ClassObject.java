package classUse;
/*
 * Description:   使用Class实例化一个对象
 * 语法：   static  Class  forName(String className); 
 *     @1、会返回一个与参数className指定的类相关的Class对象。
 *     @2、这个对象调用newInstance()方法，就可以实例化要给className类的对象。
 *     注意：使用Class对象调用newInstance()方法实例化一个className类的对象时，className类必须有无参的构造方法
 */
import java.lang.reflect.*;
class Rect{   //矩形
	private double width,height,area;
	
	Rect(){
		
	}
	
	public double getArea(){
		area = width * height;
		return area;
	}
	
	public void setWidth(double width){
		this.width = width;
	}
	
	public void setHeight(double height){
		this.height = height;
	}
}

class Circle{  //圆形
	private double radius,area;
	
	Circle(){
		
	}
	
	public double getArea(){
		area = radius* radius* 3.14;
		return area;
	}
	
	public void setRadius(double radius){
		this.radius = radius;
	}
}
public class ClassObject {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        
        try{
        	Class<?> cs = Class.forName("Rect");
        	Rect rect = (Rect)cs.newInstance();
        	rect.setWidth(100);
        	rect.setHeight(100);
        	System.out.println("Rect的面积"+rect.getArea());
        	
        	cs = Class.forName("Circle");
        	Circle circle = (Circle)cs.newInstance();
        	circle.setRadius(100);
        	System.out.println("circle的面积"+circle.getArea());
        	
        	cs = Class.forName("java.util.Date");
        	java.util.Date date = (java.util.Date)cs.newInstance();
        	System.out.println("现在的时间是: "+date.toString());
        }
        catch(Exception e){
        	System.out.println("在使用Class实例化一个对象是出现错误!");
        	System.out.println("错误信息为:" + e.toString());
        }
        
	}

}
