package practice.genericParadigm;
/*
 * Description:   HashMap<K,V>泛型类练习，类似php中 ”键->值“ 的数组
 */
import java.util.*;

class Book{
	String ISBN;
	String name;
	
	Book(String ISBN, String name){
		this.ISBN = ISBN;
		this.name = name;
	}
}

public class HashMapPractice {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        Book book1 = new Book("10001", "平凡的世界"),
        	 book2 = new Book("10002", "苏菲的世界"),
        	 book3 = new Book("10003", "Java编程语言");
        
        String key = "10002";
        HashMap<String, Book> bookList = new HashMap<String ,Book>();
        bookList.put(book1.ISBN, book1);
        bookList.put(book2.ISBN, book2);
        bookList.put(book3.ISBN, book3);
        
        if(bookList.containsKey(key)){
        	System.out.println(bookList.get(key).name+ "有货");
        }
        
        Book b = bookList.get("10003");
        System.out.println("书名："+ b.name+ "ISBN:"+ b.ISBN);
        
        int number = bookList.size();
        System.out.println("散列映射中有"+ number+ "个元素");
        
        Collection<Book> collection = bookList.values();
        Iterator<Book> iter = collection.iterator();
        while(iter.hasNext()){
        	Book te = iter.next();
        	System.out.printf("书名： %s, ISBN: %s\n", te.name, te.ISBN);
        }
	}

}
