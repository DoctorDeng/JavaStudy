package proxyPractice;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import proxyPractice.InvocationHandler;
import proxyPractice.TimeHandler;
import proxyPractice.jdkProxy.Proxy;

/**
 * 汽车时间代理
 * @author Doctor邓
 *
 */
public class Client {

	public static void main(String[] args) throws InvocationTargetException {
		/*Car car = new Car();
		car.mone();*/
	/*	Car2 car2 = new Car2();
		car2.mone();*/
		//使用聚合方式
	/*	Car car = new Car();
		Moveable m = new CarTimeProxy(car);
		m.mone();*/
	/*	Car car = new Car();
		CarLogoProxy carLogo = new CarLogoProxy(car);
		CarTimeProxy carTime = new CarTimeProxy(carLogo);
		carTime.mone();*/
		//JDK动态代理测试
		/*Car car = new Car();
		InvocationHandler h = new TimeHandler(car);
		Class<?> cls = car.getClass();
		*//**
		 * loader 类加载器
		 * interfaces 实现接口
		 * h InvocationHandler事务处理器
		 *//*
		Moveable m = (Moveable)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), h);
		m.mone();*/
	/*	
		CglibProxy proxy = new CglibProxy();
		Train t = (Train)proxy.getProxy(Train.class);
		t.move();*/
		/*Car car = new Car();
		Class<?> cls = car.getClass();
		InvocationHandler h = new TimeHandler(car);
		Moveable m = (Moveable) Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),h);
		
		System.out.println(m.getClass().getName());
		m.mone();*/
		try {
			Car car = new Car();
			InvocationHandler h = new TimeHandler(car);
			Moveable m = (Moveable)Proxy.newProxyInstance(Moveable.class,h);
			m.mone();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
	}

}
