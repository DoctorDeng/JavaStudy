package foundationEnhancement.proxy;

/**
 * Description: 通知类接口类似于 Spring 的前置通知后置通知。。。
 *
 * @author Doctor邓
 * @since 2016/12/6 21:59
 */
public interface Advice {
    Object beforeAdvice();
    Object afterAdvice();
    Object aroundAdvice();
}
