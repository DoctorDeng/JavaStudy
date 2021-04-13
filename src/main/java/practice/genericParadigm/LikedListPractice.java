package practice.genericParadigm;
/*
 * Description:  练习LinkedList<E>泛型类
 */

import java.util.LinkedList;

class Student{
	String name;
	int score;
	Student(String name, int score){
		this.name = name;
		this.score = score;
	}
}

public class LikedListPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
        LinkedList<Student> stuList = new LinkedList<Student>();
        Student stu1 = new Student("张三",78),
        		stu2 = new Student("李四",98),
        		stu3 = new Student("王五",67);
        
        stuList.add(stu1);
        stuList.add(stu2);
        stuList.add(stu3);
        
        int number = stuList.size();    //size()返回链表的长度，即节点个个数。
        System.out.println("现在链表中有"+number+"个节点");
        
        for(int i=0; i<number; i++){
        	Student temp = stuList.get(i);//get()得到链表中指定位置处节点中的对象
        	System.out.printf("第"+i+"节点中的数据, 学生：%s, 分数：%d\n", temp.name, temp.score);
        }
        
        Student removeStu = stuList.remove(1);//remove()删除指定位置上的节点,并返回被删除节的的对象
        System.out.printf("被删除的节点中的数据时:%s, %d\n", removeStu.name, removeStu.score);
     
        //set()将当前链表指定位置节点中的对象替换为指定对象，并返回被替换的对象
        Student replaceStu = stuList.set(1,new Student("赵六",54));       
        System.out.printf("被替换的节点的数据时:%s, %d\n", replaceStu.name, replaceStu.score);
        
        number = stuList.size();
        System.out.println("现在链表中有"+number+"个节点");
        for(int i=0; i< number; i++){
        	Student temp = stuList.get(i);
        	System.out.printf("第"+i+"个节点中的数据, 学生: %s, %d\n", temp.name, temp.score);
        }
        
        if(stuList.contains(stu1)){ //判断链表节点中是否有节点含有指定对象
        	System.out.println("链表包含"+stu1+": ");
        	System.out.println(stu1.name+","+stu1.score);
        }
        else{
        	System.out.println("链表没有节点含有"+stu1);
        }
	}

}
