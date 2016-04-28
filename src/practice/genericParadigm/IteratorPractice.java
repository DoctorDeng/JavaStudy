package practice.genericParadigm;
/*
 * Description:   练习泛型类Iterator<E>实现遍历链表,一个链表对象可以使用iterator()方法返回一个Iterator<E>类型的对象,
 *                该对象中每个数据成员刚好是链表节点中的数据,而起这些数据成员是按顺序存放在Iterator对象中的。
 */
import java.util.*;

class StudentTest{
	String name;
	int number;
	float score;
	StudentTest(String name, int number, float score){
		this.name = name;
		this.number = number;
		this.score = score;
	}
}

public class IteratorPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        LinkedList<StudentTest> stuList = new LinkedList<StudentTest>();
        StudentTest stu_1 = new StudentTest("张三", 9012, 85.2f),
        		    stu_2 = new StudentTest("李四", 9013, 58.6f),
        		    stu_3 = new StudentTest("王五", 9014, 98.4f),
        		    stu_4 = new StudentTest("赵六", 9015, 88.8f);
        
        stuList.add(stu_1);
        stuList.add(stu_2);
        stuList.add(stu_3);
        stuList.add(stu_4);
        
        Iterator<StudentTest> iter =  stuList.iterator();
        while(iter.hasNext()){
        	StudentTest te = iter.next();
        	System.out.println("学生编号: "+ te.number+ "学生姓名: "+ te.name+ "分数: "+ te.score);
        }
        
        System.out.println();
        IteratorCallbacks iterCall = new IteratorCallbacks();
        iterCall.verifyCallback(stuList);
        System.out.println("新添加的信息为: ");
        System.out.println("学生编号: "+ stuList.get(4).number+ "学生姓名: "+ stuList.get(4).name+ "学生姓名: "+ stuList.get(4).score);
	}
    
}

//Collection<E>实现接口回调
class IteratorCallbacks{
	
	public void verifyCallback(Collection<StudentTest> stuList){
		stuList.add(new StudentTest("ceshi", 0001,  100.0f));
	}
}