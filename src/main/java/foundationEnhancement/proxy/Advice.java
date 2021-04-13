package foundationEnhancement.proxy;

import java.lang.reflect.Method;

/**
 * Description: 通知类接口类似于 Spring 的前置通知后置通知。。。
 * @author Doctor邓
 * @since 2016/12/6 21:59
 */
public interface Advice {
    // 前置通知
    Object beforeAdvice(Method method, Object[] args);
    // 后置通知
    Object afterAdvice(Method method, Object[] args);
}
