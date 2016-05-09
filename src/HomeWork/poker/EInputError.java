package HomeWork.poker;
/*
 * 玩家输入错误异常
 */
public class EInputError extends Exception {

	public String getMessage() {
		return "玩家的ID为整数,请重新输入";
	}
}
