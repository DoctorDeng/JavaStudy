package system.lanqiao;
/* 
 * Description:  蓝桥系统

 * Function:     1、输入Java工程师资料
 *               2、删除指定Java工程师资料
 *               3、查询Java工程师资料
 *               4、修改Java工程师资料
 *               5、计算Java工程师月薪                 
 *               6、保存新添加的工程师信息             (未完工)
 *               7、对Java工程师信息排序              (未完工)
 *               8、输出所有Java工程师信息          (未完工)
 *               9、清空所有Java工程师信息          (未完工)
 *               10、从文件导入Java工程师信息    (未完工)
 *               
 *Version:       1.0beta(第一次修改)
 *Author:        Doctor邓
 *Time:          2016-4-10
 */
import java.util.Scanner;
public class JavaEngineer {
	
	//以下是Java工程师资料，在输入Java工程师资料时输入的内容
	static int engNo=-1;                    //Java工程师编号
	static String engName="";               //Java工程师姓名
	static int engSex=-1;                   //Java工程师性别（1 代表男，2 代表女）
	static int engEdu=-1;                   //Java工程师性别（1 代表大专，2 代表本科，3 代表硕士， 4 代表博士， 5 代表其他）
	static int basSalary=3000;              //Java工程师底薪
	static double insurance=3000*0.105;     //Java工程师月应扣保险金额
	
	//以下是Java工程师月工作情况资料，在计算Java工程师月薪时再输入
	static int comResult=100;               //Java工程师月工作完成分数（最小值为0， 最大值为150）
	static double workDay=22;               //Java工程师月实际工作天数
	
	//以下是由Java工程师资料和Java工程师月工作情况资料计算出来的Java工程师的底薪
	static double engSalary=0.0;            //Java工程师月薪
	
	static int userSel;                     //用户在主界面上选择的输入，对应相应功能
	static boolean status=false;            //status表示Java工程师资料是否输入完毕
	                                        //注意：不包括Java工程师月工作情况资料
	
	static Scanner input=new Scanner(System.in);
	
	public static void main(String[] args) {
		while(true){
			userSel=showMenu();             //调用showMenu()方法获得用户输入
			switch(userSel)
			{
			case 1:
				System.out.println("请现在输入Java工程师资料");
				inputEngInf();              //调用方法输入Java工程师资料
				break;
			case 2:
				System.out.println("正在删除Java工程师资料...");
				deleteEngInf();             //调用方法删除Java工程师资料
				break;
			case 3:
				System.out.println("正查询Java工程师资料...");
				searchEngInf();             //调用方法查询Java工程师资料
				break;
			case 4:
				System.out.println("正修改Java工程师资料...");
				modifyEngInf();             //调用方法修改Java工程师资料
				break;
			case 5:
				//调用方法计算Java工程师薪水，计算前需要获取月工作完成分数和月实际工作天数两个数值
				calEngSalary();
				break;
			case 6:
				System.out.println("此功能模块功能还没有实现");
				break;
			case 7:
				System.out.println("此功能模块功能还没有实现");
				break;
			case 8:
				System.out.println("此功能模块功能还没有实现");
				break;
			case 9:
				System.out.println("此功能模块功能还没有实现");
				break;
			case 10:
				System.out.println("此功能模块功能还没有实现");
				break;
			case 11:
				System.out.println("程序结束!");
				break;
		    default:
		    	System.out.println("输入错误，请重新输入！");
		    	break;
			}
			
			//当用户输入0时退出while循环，结束程序
			if(userSel == 0){
				break;
			}
		}
	}
	
	//主界面菜单
	public static int showMenu(){
		System.out.println("------------------------------------------------------------------");
		System.out.println("|                      蓝桥计划Java工程师管理系统                                                      |");
		System.out.println("------------------------------------------------------------------");
		System.out.println("1.输入Java工程师资料");
		System.out.println("2.删除指定Java工程师资料");
		System.out.println("3.查询Java工程师资料");
		System.out.println("4.修改Java工程师资料");
		System.out.println("5.计算Java工程师月薪");
		System.out.println("6.保存新添加的工程师资料");
		System.out.println("7.对Java工程师信息排序（1编号升序，2姓名升序）");
		System.out.println("8.输出所有Java工程师信息");
		System.out.println("9.清空所有Java工程师数据");
		System.out.println("10.打印Java工程师数据报表");
		System.out.println("11.从文件重新导入Java工程师数据");
		System.out.println("0.结束（编辑工程师信息后提示保存）");
		System.out.println("请输入你的选择：");
		System.out.println("您的选择是：");
		userSel=input.nextInt();
		return userSel;
	}
	
	//功能1:输入Java工程师资料
	public static void inputEngInf(){
		
		if(status == true){                                          //表示Java工程师资料已输入完毕     
			System.out.println("Java工程师资料已输入完毕，可以选择4进行修改！");
		}else{
			while(!status){                                         //如果Java工程师信息输入不完整，则全部从新输入
				 
				 System.out.print("请输入Java工程师的编号: ");
				 engNo = input.nextInt();
				 if(engNo <= 0){                                    //Java工程师编号不能为负值
					 System.out.print("Java工程师编号不能为负值！");
					 continue;                                      //跳出本次循环，执行下一次输入Java工程师资料循环
				 }
				 else{
					 status=true;                                   //资料输入正确
				 }
				 
				 System.out.print("请输入Java工程师的姓名: ");
				 engName=input.next();
				 if(engName.length() == 0){                        //长度为0，即没有输入任何信息
					 status=false;                                 //Java工程师信息输入不正确
				     System.out.print("Java工程的姓名不能为空！");
				     continue;                                     //跳出本次循环，执行下一次输入Java工程师资料循环
				 }
				 else{
					 status=true;                                   //资料输入正确
				 }
				 
				 System.out.println("请输入Java工程师的性别(1 代表男 ,2代表女)");
				 engSex = input.nextInt();
				 
				 //判断性别是否输入错误
				 if(engSex !=1 && engSex !=2){
					 status=false;
					 System.out.print("性别只能输入1或2！");
					 continue;
				 }
				 else{
					 status = true;                                //资料输入正确                                                         
				 }
				 
				 System.out.print("请输入Java工程师的学历: (1 代表大专，2 代表本科，3 代表硕士， 4 代表博士， 5 代表其他) ");
				 engEdu = input.nextInt();
				 
				 //判断学历是否输入错误
				 if(engEdu !=1 && engEdu !=2 && engEdu !=3 && engEdu !=4 && engEdu !=5){
					 status = false;
					 System.out.print("学历只能输入1、2、3、4、5(1 代表大专，2 代表本科，3 代表硕士， 4 代表博士， 5 代表其他) !");
					 continue;
				 }
				 else{
					 status = true;                                //资料输入正确 
				 }
				 
				 System.out.print("请输入Java工程师的底薪: ");
				 basSalary = input.nextInt();
				 if(basSalary <=0){
					 status = false;
					 System.out.print("Java工程师的底薪不能为负值！");
					 continue;
				 }
				 else{
					 status = true;                                
				 }
				 
				 System.out.print("请输入Java工程师月应扣保险金额: ");
				 insurance = input.nextDouble();
				 if(insurance <=0){
					 status = false;
					 System.out.print("Java工程师的月应扣保险金额不能为负值！");
					 continue;
				 }
				 else{
					 status = true;                                
				 }
			}                                          
		}
	}
	
	//功能2:删除指定Java工程师资料
	public static void deleteEngInf(){
		if(status ==false){
			System.out.println("Java工程师资料未输入或已删除！");
		}
	}
	
	//功能3:查询指定Java工程师资料
	public static void searchEngInf(){}
		
	//功能4:修改指定Java工程师资料
	public static void modifyEngInf(){}
	
	//功能5:计算指定Java工程师资料
	public static void calEngSalary(){
		if(status == false){
			System.out.println("Java工程师资料未输入或已删除，不能计算！");
		}
		else{
			while(true){
				System.out.println("请输入Java工程师月工作完成分数(最小值为0, 最大值为150)");
				comResult =input.nextInt();
				
				if(comResult<0 || comResult>150){
					System.out.println("输入错误，请重新输入!");
					continue;
				}
				else{
					break;
				}
			}
			
			while(true){
				System.out.println("请输入Java工程师月实际工作天数(最小值为0, 最大值为31):");
				workDay = input.nextDouble();
				
				if(workDay<0 || workDay>31){
					System.out.println("输入错误，请重新输入！");
					continue;
				}
				else{
					break;
				}
			}
			
			//计算calEngSalaryValue()方法计算Java工程师月薪
			engSalary = calEngSalaryValue(basSalary,comResult,workDay,insurance);
			System.out.println("Java工程师"+engName+"月薪为: "+engSalary);
		}
	}
	
	//计算Java工程师月薪
	public static double calEngSalaryValue(int basSalary,int comResult,double workDay,double insurance){
		return basSalary+basSalary*025*comResult/100+15*workDay-insurance;
	}
}
