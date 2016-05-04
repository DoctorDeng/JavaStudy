package HomeWork.exception;
/*
 * Description:   输入类型错误异常
 */
public class OrderErrorException extends Exception {
	
	private static final long serialVersionUID = -172294593668280374L;

	public String getMessage(){
		return "命令错误异常";
	}
}
