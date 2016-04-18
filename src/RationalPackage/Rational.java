package RationalPackage;
/*
 * Description:    实现对有理数(分数)的封装,使得有理数在经过四则运算(加、减、乘、除)后任然是有理数
 * 
 * 成员变量：                     numerator（分子） 、denominator（分母）
 * 四个方法：                     Rational add(Rational r)方法：有理数的加法运算
 *                 Rational sub(Rational r)方法：有理数的减法运算  
 *                 Rational muti(Rational r)方法：有理数的乘法运算
 *                 Rational div(Rational r)方法：有理数的除法运算
 *                 
 * Author:         Doctor邓
 * Time:           2016-4-18
 */       

public class Rational {
	private int numrator = 1;               //分子
	private int denominator = 1;           //分母
	private int endNumrator;
	private int endDenominator;
	
	Rational(int numrator,int denominator){
		this.numrator = numrator;
		this.denominator = denominator;
		
	}
	Rational(){
		
	}
	
	public int getNumrator(){
		return numrator;
	}
	
	public int getDenominator(){
		return denominator;
	} 
	
	public void setNumrator(int numrator) {
		this.numrator = numrator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	
	//计算分子和分母的最大公约数
	public int commonDivisor(int a,int b){          
	
		//防止为0的情况出现错误
		if(a == 0){
			return 1;
		}
		
		if(a < b){
			int c = a;
			a = b;
			b = c;
		}
		
		int r = a%b;
		while(r != 0){
			a = b;
			b = r;
			r = a%b;
		}
		return b;
	}
	
	//求得分子分母约分后的值
	public void setValue(int numrator,int denominator){
		
		int c= commonDivisor(numrator,denominator);      
		this.endNumrator = this.endNumrator/c;
		this.endDenominator = this.endDenominator/c;
		
	}
	
	//求最小公倍数
	public int commonMultiple(int a,int b){
		return (a*b)/commonDivisor(a,b);
	}
	
	
	//有理数加法
	public void  add(Rational x, Rational y){
		int a = commonMultiple(x.denominator, y.denominator);
		int b = a/x.denominator;
		int c = a/y.denominator;
		this.endNumrator = x.numrator*b + y.numrator*c;
		this.endDenominator = a;
		setValue(this.endNumrator,this.endDenominator);
		System.out.println(x.numrator+"/"+x.denominator+" + "+y.numrator+"/"+y.denominator+"  的值为: "+this.endNumrator+"/"+this.endDenominator);
	}
	
	//有理数减法
	public void sub(Rational x, Rational y){
		int a = commonMultiple(x.denominator, y.denominator);
		int b = a/x.denominator;
		int c = a/y.denominator;
		this.endNumrator = x.numrator*b - y.numrator*c;
		this.endDenominator = a;
		setValue(this.endNumrator,this.endDenominator);
		System.out.println(x.numrator+"/"+x.denominator+" - "+y.numrator+"/"+y.denominator+"  的值为: "+this.endNumrator+"/"+this.endDenominator);
	}
	
	//有理数乘法
	public void muti(Rational x, Rational y){
		this.endNumrator = x.numrator * y.numrator;
		this.endDenominator = x.denominator * y.denominator;
		setValue(this.endNumrator,this.endDenominator);
		System.out.println(x.numrator+"/"+x.denominator+" * "+y.numrator+"/"+y.denominator+"  的值为: "+this.endNumrator+"/"+this.endDenominator);
	}
	
	//有理数除法
	public void div(Rational x, Rational y){
		this.endNumrator = x.numrator * y.denominator;
		this.endDenominator = x.denominator * y.numrator;
		setValue(this.endNumrator,this.endDenominator);
		System.out.println(x.numrator+"/"+x.denominator+" ÷ "+y.numrator+"/"+y.denominator+"  的值为: "+this.endNumrator+"/"+this.endDenominator);
	}
	
}
