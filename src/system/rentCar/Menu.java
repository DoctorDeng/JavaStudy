package system.rentCar;
import java.util.Scanner;
public class Menu {
	//定义一个二维数组存储对应车序号的租金
	int[][] rentMoney= new int[6][1];
	
	//系统开始菜单，询问用户时候需要租车
	public void carMenu(){
		Scanner input= new Scanner(System.in);
		System.out.println("欢迎使用达达租车系统：");
	    System.out.println("您是否要租车：1是,其他退出");
	    int lock=input.nextInt();
	    if(lock==1){
	    	System.out.println("您可租的类型及其价目表：");
	    	System.out.println("序号\t汽车名称 \t租金\t容量  ");
	    }
	    else{
	    	//退出当前程序
	    	System.exit(0);
	    }
	}
	
	    //定义方法，存储各出租车对象信息
		private  Car[] carList(){
			
			//创建一个对象数组，来存储各个出租车对象
			Car cars[]={new Bus(1,"奥迪A4",500,4),
					    new Bus(2,"马自达6",400,4),
					    new Picard(3,"皮卡雪6",450,4,2),
					    new Bus(4,"金龙",800,20),
					    new Van(5,"松花江",400,4),
					    new Van(6,"依维柯",1000,20)};
			return cars;
		}
		
		//获取出租车信息
		public Car[] getCarList(){
			return carList();
		} 
		
		//显示出租车信息列表
		public void showCar(){
			for(int i=0;i<6;i++){
		    Car cars[]=this.carList();
		    System.out.println(cars[i].toString()); 
			}
		}
	
}
