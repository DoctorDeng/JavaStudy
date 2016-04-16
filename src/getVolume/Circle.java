package getVolume;
/*
 * Description:  圆型柱体
 */
public class Circle extends Geometry {
    double r;    //圆半径
    
    Circle(double r){
    	this.r= r;
    }
	
    //返回圆形柱体底面积
	public double getArea() {
		// TODO 自动生成的方法存根
		return (3.14*r*r);
	}

}
