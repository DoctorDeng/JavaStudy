package proxy;

import java.lang.reflect.Method;

public interface InvocationHandler {
	public Object invoke(Object o,Method m);
}
