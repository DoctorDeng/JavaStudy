package practice.genericParadigm;
/*
 * Description:   练习HashSet<E>泛型类的交、并、差。
 */

import java.util.HashSet;
import java.util.Iterator;

public class HashSetPractice1 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        Integer one = new Integer(1);
        Integer two = new Integer(2);
        Integer three = new Integer(3);
        Integer four = new Integer(4);
        Integer five = new Integer(5);
        Integer six = new Integer(6);
        
        HashSet<Integer> A = new HashSet<Integer>(),
                         B = new HashSet<Integer>(),
                   tempSet = new HashSet<Integer>();
        
        A.add(one);
        A.add(two);
        A.add(three);
        A.add(four);
        A.add(five);
        A.add(six);
        
        B.add(one);
        B.add(two);
        
        tempSet = (HashSet<Integer>) A.clone();
        A.removeAll(B);
        B.removeAll(tempSet);
        B.addAll(A);  
        int number = B.size();
        System.out.println("A 和 B 的对称差集合有"+ number+ "个元素：");
        Iterator<Integer> iter= B.iterator();
        while(iter.hasNext()){
        	Integer te = iter.next();
        	System.out.printf("%d, ", te.intValue());
        }
        System.out.println();
        
        number = A.size();
        System.out.println("A 和 B 的差集有"+ number+ "个元素");
        Iterator<Integer> iter1 = A.iterator();
        while(iter1.hasNext()){
        	Integer temp = iter1.next();
        	System.out.printf("%d, ",temp.intValue());
        }
	}

}
