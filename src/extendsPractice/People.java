package extendsPractice;
/*
 * Description:  父类。
 *           成员变量 :    
 *                   height:  身高
 *                   weight:  体重
 *           方法: 
 *                   speakHello()      问候方法
 *                   averageHeight()   平均身高      
 *                   averageWeight()   平局体重   
 * Author:   Doctor邓
 * Time:     2016-4-18
 */                
public class People {
	protected double height;
	protected double weight;
	
	public void averageHeight(){
		height= 173;
		System.out.println("Average height:"+height);
	}
	
	public void averageWeight(){
		weight = 70;
		System.out.println("Average weight:"+weight);
	}
	

}
