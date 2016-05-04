package HomeWork.exception;
/*
 * Description:   图书不存在异常
 */
public class BookNotFoundException extends Exception{
	
	private static final long serialVersionUID = -2360407061390591216L;

	public String getMessage(){
		return "图书不存在";
	}
}
