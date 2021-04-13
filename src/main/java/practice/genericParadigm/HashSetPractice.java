package practice.genericParadigm;
/*
 * Description:   练习HashSet<E>泛型类
 *         说明:   HashSet<E>泛型类在数据组织上类似数学上的集合，可以进行“交”、“并”、“差”等运算。
 */

import java.util.HashSet;
import java.util.Iterator;

class StudentHash{
	String name;
	int score;
	
	StudentHash(String name ,int score){
		this.name = name;
		this.score = score;
	}
	
}
public class HashSetPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        StudentHash zs = new StudentHash("张三", 58),
        		    ls = new StudentHash("李四", 99),
        		    ww = new StudentHash("王五", 86);
        
        HashSet<StudentHash> stuHash = new HashSet<StudentHash>();
        HashSet<StudentHash> stuSet = new HashSet<StudentHash>();
        
        stuHash.add(zs);
        stuHash.add(ls);
        stuHash.add(ww);
        
        stuSet.add(zs);
        stuSet.add(ww);
        
        if(stuHash.contains(ls)){
        	System.out.println("集合stuHash中有"+ls.name);
        }
        
        if(stuHash.containsAll(stuSet)){
        	System.out.println("集合stuHash中有集合stuSet");
        }
        
        int number = stuSet.size();
        System.out.println("stuSet集合包含有"+number+"个元素: ");
        Object s[] = stuSet.toArray();
        for(int i=0; i<number; i++){
        	System.out.printf("姓名：%s, 分数：%d\n", ((StudentHash)s[i]).name, ((StudentHash)s[i]).score);
        }
        
        number = stuHash.size();
        System.out.println("stuHash中有"+ number+ "个元素： ");
        Iterator<StudentHash> iter = stuHash.iterator();
        while(iter.hasNext()){
        	StudentHash stuTemp = iter.next();
        	System.out.printf("姓名：%s, 分数：%d\n", stuTemp.name, stuTemp.score);
        }
	}

}
