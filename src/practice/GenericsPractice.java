package practice;
/*
 * Description:  继续练习泛型
 * Function:     计算不同底面的锥形柱体的体积
 */

class Cone<E>{
	double height;
	E bottom;
	
	public Cone(E bottom){
		this.bottom= bottom;
	}
	
	public void getVolume(){
		String s=bottom.toString();
		double area= Double.parseDouble(s);       //此方法返回的字符串参数表示的double值.
		System.out.println("体积是:"+1.0/3.0*area*height);
	}
}

class Circle{   //圆形
	double radius;   //圆半径
	double area;     //圆面积
	
	public Circle(double radius){
		this.radius= radius;
	}
	
	public String toString(){
		area= radius*radius*Math.PI;    //Math.PI 记录的圆周率
		return ""+area;                 //返回类型为String类型,故加上""
	}
}

class Rectangle{     //矩形
	double width;    //矩形宽
	double height;   //矩形高
	double area;     //矩形面积
	
	public  Rectangle(double width, double height){
		this.width= width;
		this.height= height;
	}
	
	public String toString(){
		area= width*height;
		return ""+area;
	}
}
public class GenericsPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//圆底面锥体积
		Circle circle= new Circle(5);
		Cone<Circle> cone1= new Cone<Circle>(circle);
		cone1.height= 10;
		cone1.getVolume();
        
		//矩形底面锥体积
		Rectangle rectangle= new Rectangle(10,5);
		Cone<Rectangle> cone2= new Cone<Rectangle>(rectangle);
		cone2.height= 10;
		cone2.getVolume();
	}

}
