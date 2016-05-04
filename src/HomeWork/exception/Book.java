package HomeWork.exception;

/*
 * Description:   借书系统图书类
 *        
 */
public class Book{
	int id;
	String name;
   
	Book(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String toString(){
		return "序号: " + this.id + "书名: " + this.name;
	}
}
