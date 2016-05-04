package HomeWork.exception;

/*
 * Description:    图书信息类
 */
public class BookInfor {
	
	//创建一个Book对象数组来存储系统图书对象
	private Book  bookList[]= {new Book(1, "高数"),
							   new Book(2, "语文"),
							   new Book(3, "平凡的世界"),
							   new Book(4, "苏菲的世界"),
							   new Book(5, "Java")};
	
	//获取图书列表信息
	public  Book[] getBookList(){
		return bookList;
	}
	
	//显示全部图书信息
	public void showBookList(){
		for (int i = 0; i < bookList.length; i++) {
			System.out.println( bookList[i].toString() );
		}
	}
	
}
