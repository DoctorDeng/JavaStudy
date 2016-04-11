package practice;

public class ConstructionMethod{
	int x;
	int y;
	public  ConstructionMethod(){
		System.out.println("无参的构造方法!");
	}
	public ConstructionMethod(int newX,int newY){
		if(x<3){
			System.out.println("您输入的参数有问题，自动赋值为:3");
			x=3;
		}else{
			x=newX;
			y=newY;
		}
		System.out.println("有参的构造方法执行了!");		
	}
}
