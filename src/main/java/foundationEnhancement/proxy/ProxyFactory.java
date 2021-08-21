package foundationEnhancement.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:
 *
 * @author doctordeng
 * @since 2016/12/7 21:44
 */
public class ProxyFactory {
    // 动态获取代理类
    public static Object getProxy(final Object target, final Advice advice) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        InvocationHandler handler = (Object proxy, Method method, Object[] args) ->{
            advice.beforeAdvice(method, args);
            Object obj = method.invoke(target, args);
            advice.afterAdvice(method, args);
            return obj;
        };
        Class clazzProxy = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
        Object  object   = clazzProxy.getConstructor(InvocationHandler.class).
                newInstance(handler);

        return object;
    }
}
