package gatherFrame;

import java.util.*;
import java.util.Map.Entry;

/*
 * Description:   练习Hash接口及其实现类HashMap的使用
 */
public class MapTest {
	Scanner input = new Scanner(System.in);
	//用来装载学生类型对象
	public Map<String, Student> students;
	
	public MapTest() {
		this.students = new HashMap<String, Student>();
	}
	
	/*
	 * 测试添加：输入学生ID，判断是否被占用
	 * 若未被占用，则输入姓名，创建新学生对象，并且添加到students中
	 */
	public void testPut() {
		
		
		int i = 0;
		while ( i < 3) {
			System.out.println("请输入学生ID: ");
			if (input.hasNext()) {
				String ID = input.next();
				
				//判断ID是否被占用
				Student st = students.get(ID);
				if (st == null) {
					System.out.println("请输入学生姓名: ");
					
					if(input.hasNext()) {
						String name = input.next();
						
						//创建新的学生对象
						Student newStudent = new Student(ID, name);
						//通过调用students的put方法,添加ID-学生映射
						students.put(ID, newStudent);
						System.out.println("成功添加学生:" + students.get(ID).getName());
						i++;
					}
				}
				else {
					System.out.println("该学生ID已被占用");
				}
			}
		}
	}
	
	/*
	 * 测试Map的keySet方法
	 */
	public void testKeySet() {
		//通过keySet方法,返回Map中所有“键”的set集合
		Set<String> keySet = students.keySet();
		
		//取得students的容量
		System.out.println("总共有: " + students.size() + "个学生!");
		
		//遍历keySet，取得每一个键，在调用get方法取得每个键对应的value的值
		for (String stuId : keySet ) {
			Student st = students.get(stuId);
			
			if (st != null) { System.out.println("学生: " + st.getName() );}
		}
	}
	
	/*
	 * 测试删除Map中的映射
	 */
	public void testRemove() {
		
		while (true) {
			System.out.println("请输入要删除的学生ID！");
			String id = input.next();
			Student st = students.get(id);
				
			if(st == null) {
				System.out.println("该ID不存在");
				continue;
			}
				
			students.remove(id);
			System.out.println("成功删除学生: " + st.getName());
			break;
		}
		input.close();
	}
	
	/*
	 * 利用put方法修改Map中的已有映射
	 */
	public void testModify() {
		
		System.out.println("请输入要修改的学生ID :");
		if( input.hasNext() ) {
			while (true) {
				String stuID = input.next();
				
				Student student = students.get(stuID);
				if ( student == null ) {
					System.out.println("该ID不存在! 请从新输入!");
				    continue;
				}
				
				System.out.println("当前学生ID，所对应的学生为 ：" + student.getName() );
				System.out.println("请输入新的学生姓名:");
				String name = input.next();
				
				Student newStudent = new Student(stuID, name);
				students.put(stuID, newStudent);
				System.out.println("修改成功!");
				break;
				
			}
		}
	}
	
	
	/*
	 * 通过enterSet方法来遍历Map,返回Map中的所有键-值对
	 */
	public void testEntrySet() {
		Set<Entry<String, Student>> entrySet = students.entrySet(); //entrySet():返回的是Set类型的对象
		
		for (Entry<String, Student> entry : entrySet) {
			
			System.out.println("取得键:" + entry.getKey());
			System.out.println("对应的值为: " + entry.getValue().getName());  
			//getValue()：这里返回的是对象额，故要使用对象的方法来获取对象的姓名
		}
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		MapTest mt = new MapTest();
		mt.testPut();
		mt.testKeySet();
		//mt.testRemove();
		//mt.testEntrySet();
		mt.testModify();
		mt.testEntrySet();
	}

}
