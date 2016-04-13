package system.rentCar;
import java.util.Scanner;
public class CarSystem{
	
	//定义一个二维数组来存取用户的租车信息，包括：租车序号，租车数量，租车天数
	public static int[][] infor =new int[6][3]; 
	static int carIDs;           //车序号
	static int carNumbers;       //对应序号租车数量
	static int rentDays;         //对应序号租车天数
	
	//定义一个方法来获取用户租车信息并计算最终的所需金额
	public static void rent(){
		
		boolean lock= true;
		Scanner input = new Scanner(System.in);
		while(lock){
			   System.out.println("请输入您想要的汽车的序号:");
		       int carID= input.nextInt();
		       if(carID>0 && carID <7){
		    	   carIDs=carID;
		       }
		       else{
		    	   System.out.println("输入错误请重新输入");
		       }
		       System.out.println("请输入您想要的汽车的数量：");
		       int carNumber= input.nextInt();
		       System.out.println("请输入您想租车的天数：");
		       int  rentDay= input.nextInt();
		       System.out.println("输入1继续选择租车,其他结账！");
		} 
	       input.close();
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
	   //创建一个carMenu对象，调用Menu方法显示菜单
       Menu user= new Menu();
       user.carMenu();
       user.carList();
       
	}

}
