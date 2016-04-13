package system.rentCar;
import java.util.Scanner;
public class Menu {
	//系统开始菜单，询问用户时候需要租车
	public void carMenu(){
		Scanner input= new Scanner(System.in);
		System.out.println("欢迎使用达达租车系统：");
	    System.out.println("您是否要租车：1是,其他退出");
	    int lock=input.nextInt();
	    if(lock==1){
	    	System.out.println("您可租的类型及其价目表：");
	    	System.out.println("序号    "+"  汽车名称     "+"            租金                     "+"     容量    ");
	    }
	    else{
	    	//退出当前程序
	    	System.exit(0);
	    }
	    input.close();
	}
	//显示车列表
	public void carList(){
		        //创建奥迪A4对象
				Bus aodi= new Bus();
				aodi.setCarID(1);
				aodi.setCarName("奥迪A4");
				aodi.setCarMoney(500);
				aodi.setManned(4);
				
				//创建马自达6对象
				Bus mazida= new Bus();
				mazida.setCarID(2);
				mazida.setCarName("马自达6");
				mazida.setCarMoney(400);
				mazida.setManned(4);
				
				//创建皮卡雪6对象
				Picard pikaxue= new Picard();
				pikaxue.setCarID(3);
				pikaxue.setCarName("皮卡雪6");
				pikaxue.setCarMoney(450);
				pikaxue.setManned(4);
				pikaxue.setCarGo(2);
				
				//创建金龙对象
				Bus jinlong= new Bus();
				jinlong.setCarID(4);
				jinlong.setCarName("金龙");
				jinlong.setCarMoney(800);
				jinlong.setManned(20);
				
				//创建松花江对象
				Van songhua= new Van();
				songhua.setCarID(5);
				songhua.setCarName("松花江");
				songhua.setCarMoney(400);
				songhua.setCarGo(4);
				
				//创建依维柯对象
				Van yiwei= new Van();
				yiwei.setCarID(6);
				yiwei.setCarName("依维柯");
				yiwei.setCarMoney(1000);
				yiwei.setCarGo(20);
				
				//显示车列表
				aodi.response();
				mazida.response();
				pikaxue.response();
				jinlong.response();
				songhua.response();
				yiwei.response();
	}

}
