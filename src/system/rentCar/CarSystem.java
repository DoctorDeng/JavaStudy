package system.rentCar;
import java.util.Scanner;

public class CarSystem{
	
	//定义一个二维数组来存取用户的租车信息，包括：租车序号，租车数量，租车天数
	public static int[][] infor =new int[10][10]; 
	static int carIDs;           //车序号
	static int carNumbers;       //对应序号租车数量
	static int rentDays;         //对应序号租车天数
	
	static Scanner input=new Scanner(System.in);
	
	

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
	   //创建一个carMenu对象，调用Menu方法显示菜单和car的信息
       Menu user= new Menu();
       user.carMenu();
       user.showCar();
       CarSystem.rent();
       
	}
	
	
	//定义一个方法来获取用户租车信息并计算最终的所需金额
	public static void rent(){
		
		//第一一个锁，用来控制循环输入的跳出问题
		boolean lock= true;
		
		while(lock==true){
			   System.out.println("请输入您想要的汽车的序号:");
		       int carID= input.nextInt();
		       if(carID>0 && carID <7){
		    	   carIDs=carID;        //carIDs用于确定二维数组的数组序号，对应车的序号
		    	   lock= false;
		       }
		       else{
	    	   System.out.println("输入错误请重新输入,汽车序号为：1~6哦！");
		    	   continue;
		       }
		}
		//重置lock
		lock= true;
		while(lock==true){
			   System.out.println("请输入您想要的汽车的数量：");
		       int carNumber= input.nextInt();
		       if(carNumber>0 && carNumber <100){
		    	   carNumbers= carNumber;
		    	   
		    	   //将租车数量信息存储到数组中
		    	   infor[carIDs-1][0]=carNumbers;
		    	   
		    	   lock= false;
		       }
		       else{
		    	   System.out.println("输入错误请重新输入");
		    	   continue;
		       }
		}
		lock= true;
		while(lock==true){
			   System.out.println("请输入您想租车的天数：");
		       int rentDay= input.nextInt();
		       if(rentDay>0 && rentDay <31){
		    	   rentDays=rentDay;
		    	   
		    	   //将租车天数信息存储到数组中
		    	   infor[carIDs-1][1]= rentDays;
		    	   
		    	   lock= false;
		       }
		       else{
		    	   System.out.println("租车天数为1~30天哦！");
		    	   continue;
		       }
		}
		
		System.out.println("输入1继续选择租车,其他结账！");
	    int x= input.nextInt();
	     
	    if(x==1){                //递归方法继续输入租车信息
	    	rent();
	    }
	    else{                    //结账
	    	System.out.println("您所付金钱为："+CarSystem.accounts());
	    	System.out.println("输入 1 结账并退出,其他从新输入租车信息！");
	    	int endRent = input.nextInt();
	    	if(endRent==1){
	    		System.out.println("您付了"+CarSystem.accounts()+"元,欢迎下次光临!");
	    		System.exit(0);      //退出程序
	    	}
	    	else{
	    		//清空存储的租车信息
	    		for(int i=0;i<6;i++){
	    			for(int j=0;j<2;j++){
	    				infor[i][j]=0;
	    			}
	    		}
	    		CarSystem.rent();
	    	}
	    }
	    input.close();
	    
	}
	
	
	
	//计算顾客租车金钱
	public static double accounts(){
		Menu rent= new Menu();
		//获取所有出租车对象
	    Car[] rentCar = rent.getCarList();
	    int rentMoney = 0;
	    for(int i=0;i<6;i++){
	    	rentMoney=infor[rentCar[i].getCarID()-1][0]*infor[rentCar[i].getCarID()-1][1]*rentCar[i].getCarMoney()+rentMoney;
	    }
		return rentMoney;
	}
	

}
