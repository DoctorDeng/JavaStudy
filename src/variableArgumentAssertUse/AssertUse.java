package variableArgumentAssertUse;
/*
 * Description:   练习断言(assert)语句的使用
 *         语法:   assert booleanExpression;  (当booleanExpression的值是 false 时，程序从断言语句处停止执行;当值是 true 是，程序从断言语句出继续执行)
 *                assert booleanExpression: messageException; (当booleanExpression的值是 false 时 程序从断言语句处停止执行 并输出massageException表达式的值,
 *                                                             提示用户出现了怎样的问题 ;当值是 true 是，程序从断言语句出继续执行)
 *         说明:   booleanExpression必须是求值为boolean类型的表达式,messageException可以是求值为字符的表达式;
 *         用途:   一般用于程序不准备通过捕获异常来处理的错误.
 *         注意:   一般默认关闭断言                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
 */ 
public class AssertUse {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
        int x = -6;
        assert x>0:"负数不能计算平方根!";
        double y = Math.sqrt(x);
        System.out.println(y);
	}

}
