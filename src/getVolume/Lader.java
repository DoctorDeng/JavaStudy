package getVolume;
/*
 * Description:  梯形柱体
 */
public class Lader extends Geometry {
	double a;     //梯形上底
	double b;     //梯形下底
	double h;     //梯形高
 
	Lader(double a,double b, double h){
		this.a= a;
		this.b= b;
		this.h= h;
	}
	
	//返回梯形柱体底面积
	public double getArea() {
		// TODO 自动生成的方法存根
		return ((a+b)*h)/2;
	}

}
