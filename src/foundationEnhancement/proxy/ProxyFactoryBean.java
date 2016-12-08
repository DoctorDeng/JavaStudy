package foundationEnhancement.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description: 生成动态代理的工厂, 需要为工厂类提供：目标 和 通知
 *
 * @author Doctor邓
 * @since 2016/12/7 22:10
 */
public class ProxyFactoryBean {
    private Advice advice;
    private Object target;

    public Object getProxy() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        InvocationHandler handler = (Object proxy, Method method, Object[] args) ->{
            advice.beforeAdvice(method, args);
            java.lang.Object obj = method.invoke(target, args);
            advice.afterAdvice(method, args);
            return obj;
        };
        Class clazzProxy = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
        Object  object   = clazzProxy.getConstructor(InvocationHandler.class).
                newInstance(handler);

        return object;
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
