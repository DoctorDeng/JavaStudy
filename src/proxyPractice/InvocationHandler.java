package proxyPractice;

import java.lang.reflect.Method;

public interface InvocationHandler {
	public Object invoke(Object o,Method m);
}
