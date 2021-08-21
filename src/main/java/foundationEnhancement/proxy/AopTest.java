package foundationEnhancement.proxy;

import java.util.Collection;

/**
 * Description: 测试 Aop 练习
 *
 * @author doctordeng
 * @since 2016/12/8 21:42
 */
public class AopTest {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory("aopconfig.properties");
        Collection collection = (Collection) beanFactory.getBean("arrayList");
        collection.add("你好");
    }
}
