package practice;
//1、定义一个类
public class Telphone {
 //2、属性（成员变量）有什么
	float screen;
	float cpu;
	float mem;
	int var=40;
	//3、方法干什么
	void call(){
		int localVar=0;
		int var=40;
		System.out.println("var:"+var);
		System.out.println("localVar:"+localVar);
		System.out.println("Telphone有打电话的功能!");
	}
	void sendMessage(){
		int localVar=20;
		System.out.println("localVar:"+localVar);
		System.out.println("var:"+var);
		System.out.println("screen:"+screen+"cpu:"+cpu+"mem:"+mem+"Telphone有发短信的功能!");
	}
}
