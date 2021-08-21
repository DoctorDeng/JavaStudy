package foundationEnhancement.proxy;

import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author doctordeng
 * @since 2016/12/8 21:47
 */
public class TimeAdvice implements Advice {
    @Override
    public Object beforeAdvice(Method method, Object[] args) {
        System.out.println("执行开始时间:" + System.currentTimeMillis());
        return null;
    }

    @Override
    public Object afterAdvice(Method method, Object[] args) {
        System.out.println("执行结束时间:" + System.currentTimeMillis());
        return null;
    }
}
