package thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 练习 Java 并发集合
 * @author Doctor邓
 *
 */
public class TestConcurrentCollection {
	
	public static void synchronizedMapTest() {
		Collections.synchronizedMap(null);  
	}
	
	public static void iteratorTest() {
		Collection<String> colls = new ArrayList<>();
		colls.add("张三");
		colls.add("李四");
		colls.add("王五");
		colls.add("赵六");
		colls.add("邓七");
		
		Iterator<String> itrColl = colls.iterator();
		while(itrColl.hasNext()) {
			String name = itrColl.next();
			System.out.println(name);
			/**
			 * 如果移除的是集合的倒数第二个元素则不会抛异常
			 * 另外如果是添加则始终会抛出异常
			 */
			if ("赵六".equals(name)) colls.remove(name);
		} 
	}

	public static void copyOnWriteArrayListTest() {
		//使用 CopyOnWriteArrayList 解决了在迭代过程中无法修改元素的问题
		Collection<String> colls = new CopyOnWriteArrayList<>();
		colls.add("张三");
		colls.add("李四");
		colls.add("王五");
		colls.add("赵六");
		colls.add("邓七");
		
		Iterator<String> itrColl = colls.iterator();
		while(itrColl.hasNext()) {
			String name = itrColl.next();
			System.out.println(name);
			if ("张三".equals(name)) colls.remove(name);
		} 
	}
	public static void main(String[] args) {
		//iteratorTest();
		copyOnWriteArrayListTest();
	}
}
