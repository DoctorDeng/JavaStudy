package foundationEnhancement.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Description: 专门读取配置文件, 利用反射创建对象的工厂
 *
 * @author doctordeng
 * @since 2016/12/7 22:09
 */
public class BeanFactory {
    private Properties properties = new Properties();
    public BeanFactory(InputStream inputStream) {
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BeanFactory(String configPath) {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configPath);) {
            this.properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object getBean(String name)  {
        String className = properties.getProperty(name);
        Class clazz = null;
        Object bean = null;
        try {
            clazz = Class.forName(className);
            bean  = clazz.newInstance();

            if (bean instanceof ProxyFactoryBean) {
                ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) bean;
                Advice advice = (Advice) Class.forName(properties.get(name + ".advice").toString()).newInstance();
                Object target = Class.forName(properties.get(name + ".target").toString()).newInstance();

                proxyFactoryBean.setAdvice(advice);
                proxyFactoryBean.setTarget(target);
                Object object = proxyFactoryBean.getProxy();
                return object;
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return bean;
    }
}
