package HomeWork.exception;
/*
 * Description:    借书系统主类
 */

import java.util.Scanner; 

public class BookSystem {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        BookSystem bookSystem = new BookSystem();
        try {
    		bookSystem.searchBook();
        }
        catch (BookNotFoundException e) {
        	System.out.println(e.getMessage());
        }
        catch (OrderErrorException e) {
        	System.out.println(e.getMessage());
        }
        
	}
	
	//获取用户查找方式方法
//	public int selectInput() throws OrderErrorException {
//		
//	}
	
	//查找图书方法
	public void searchBook() throws OrderErrorException, BookNotFoundException {
			
		Scanner input = new Scanner(System.in);
		
		
		//----------------判断找书的方式------------------------//
        System.out.println("输入命令: 1-按照书名查找图书; 2-按照序号查找ID");
        Integer selectInput;       //用于记录用户输入的选择查找书的方式
        
        if (input.hasNextInt()) {    //判断输入类型是否为int类型
        	selectInput = input.nextInt();
        	
        	if ( (selectInput != 1) && (selectInput !=2) ) {
        		throw(new OrderErrorException() );
        	}
        }
        else {
        	throw(new OrderErrorException());
        }
		
        //-----------------------找书----------------------------//
		int bookID;               //用户输入的书的ID
		String bookName;          //用户输入的书的名字
		BookSearch bookSearch = new BookSearch();    //初始化找书对象
	
			if(selectInput == 1) {
				System.out.println("输入书的名称:");
				bookName = input.next();
				System.out.println(bookSearch.search(bookName));
			}
			else if (selectInput == 2) {
				System.out.println("输入书的序号:");
				
				if(input.hasNextInt()) {
					bookID = input.nextInt();
					System.out.println(bookSearch.search(bookID));
				}
				else {
					throw(new OrderErrorException());
				}
			}
			else {
				System.out.println("判断搜索书的方式有错误");
			}
	}
}
