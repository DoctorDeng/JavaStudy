package HomeWork.exception;
/*
 * Description:   借书系统图书查找类
 */
public class BookSearch {
	
	private String result;
	Book bookList[] = new BookInfor().getBookList();    //获取图书信息列表
	
	public String search(int id) throws BookNotFoundException {
		
		for(int i = 0; i < bookList.length; i++){
			if (id == bookList[i].id){
				result = bookList[i].toString();
				break;
			}
			else if (i == bookList.length -1){          //当查找到最后一本书且还没有找到对应图书时抛出异常。
				throw( new BookNotFoundException());
			}
		}
		return result;
	}
	
	public String search(String name) throws BookNotFoundException {
		
		for(int i = 0; i < bookList.length; i++){
			if ( bookList[i].name.equals(name) ){
				result = bookList[i].toString();
				break;
			}
			else if (i == bookList.length -1) {
				throw( new BookNotFoundException());
			}
		}
		return result;
	}
}
