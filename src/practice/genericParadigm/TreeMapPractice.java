package practice.genericParadigm;
/*
 * Description:    联系TreeMap<E, v>泛型类
 *         说明:    TreeMap类实现了Map接口。TreeMap提供了按排序顺序存储键-值对的有效手段。
 */

import java.util.*;

class MyKey implements Comparable{
	int number = 0;
	MyKey(int number){
		this.number = number;
	}
	
	public int compareTo(Object obj){
		MyKey temp = (MyKey)obj;
		if((this.number - temp.number) == 0){
			return -1;
		}
		else{
			return (this.number - temp.number);
		}
	}
}

class Student2{
	String name = null;
	int height;
	int weight;
	
	Student2(String name, int height, int weight){
		this.name = name;
		this.weight = weight;
		this.height = height;
	}
}

public class TreeMapPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        Student2 stu1 = new Student2("张三", 170, 60),
        		 stu2 = new Student2("李四", 175, 75);
        
        TreeMap<MyKey, Student2> stuMap = new TreeMap<MyKey, Student2>();
        stuMap.put(new MyKey(stu1.weight), stu1);
        stuMap.put(new MyKey(stu2.weight), stu2);
        
        int number = stuMap.size();
        System.out.println("树映射中有: "+ number+ "个对象");
        Collection<Student2> collection = stuMap.values();
        Iterator<Student2> iter = collection.iterator();
        while(iter.hasNext()){
        	Student2 temp = iter.next();
        	System.out.printf("%s, %d(公斤)\n", temp.name, temp.weight);
        }
        stuMap.clear();
        
        stuMap.put(new MyKey(stu1.height), stu1);
        stuMap.put(new MyKey(stu2.height), stu2);
        number = stuMap.size();
        System.out.println("树映射中有"+ number+ "个对象");
        collection = stuMap.values();
        iter = collection.iterator();
        while(iter.hasNext()){
        	Student2 temp = iter.next();
        	System.out.printf("%s, %d(厘米)\n", temp.name, temp.height);
        }
	}

}
