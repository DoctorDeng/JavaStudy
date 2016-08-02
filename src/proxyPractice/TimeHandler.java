package proxyPractice;

import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {
	//被代理对象
	private Object target;
	
	public TimeHandler(Object target) {
		this.target = target;
	}
	@Override
	public Object invoke(Object o, Method m) {
		try {
			long startTime = System.currentTimeMillis(); 
			System.out.println("汽车开始行驶。。。。"); 
			m.invoke(target);
			long endTime = System.currentTimeMillis(); 
			System.out.println("汽车行驶结束...汽车行驶时间:" + (endTime - startTime)+":毫秒!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
