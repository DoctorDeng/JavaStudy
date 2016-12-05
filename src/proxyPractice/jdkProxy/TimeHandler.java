package proxyPractice.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {

	private Object target;
	
	public TimeHandler(Object target) {
		super();
		this.target = target;
	}
	/**
	 * 参数：
	 * proxy  被代理对象
	 * method 被代理对象类类型的 Method 对象
	 * args   方法的参数
	 * 返回值：
	 * Object 方法的返回值
	 */
	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		long startTime = System.currentTimeMillis();
		System.out.println("汽车开始行驶。。。。");
		arg1.invoke(target);
		long endTime = System.currentTimeMillis();
		System.out.println("汽车行驶结束...汽车行驶时间:" + (endTime - startTime)+":毫秒!");
		return null;
	}

}
