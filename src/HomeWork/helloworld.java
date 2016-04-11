package HomeWork;
/* 
 * 功能：实现接收三个班级的各四名学员的成绩信息，然后计算每个班级学员的平均分
 * 知识点：二重循环：外层循环控制班级的数量、内层循环控制每个班级学员数量
 * 
 */
import java.util.Scanner;
public class helloworld {
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        int classNum=3;   //班级数量
        int stuNum=4;     //学员数量
        int sum=0;        //成绩总和
        double avg=0;     //成绩平局分
        
        Scanner input=new Scanner(System.in);
        for(int i=1;i<=classNum;i++){
        	System.out.println("***请输入第"+i+"个班级的成绩***");
        	for(int j=1;j<=stuNum;j++){
        		System.out.println("***请输入第"+j+"个学员的成绩: ");
        		int score=input.nextInt();
        		sum=sum+score;
        	}
        	avg=sum/stuNum;
        	System.out.println("第"+i+"个班级学生的平均分为: "+avg);
        	sum=0;          //成绩归0，防止上个班级的成绩影响到下一个班级成绩的结果
        }
	}

}
