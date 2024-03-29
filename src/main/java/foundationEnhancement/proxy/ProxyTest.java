package foundationEnhancement.proxy;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Description: 练习 JDK 的动态代理相关类之Proxy
 *
 * @author doctordeng
 * @since 2016/12/3 13:56
 */
public class ProxyTest {
    public static void proxyTest() {
        // 动态创建一个实现了某个接口的代理类
        Class clazzProxy = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);

        Constructor[] constructors = clazzProxy.getConstructors();
        System.out.println("------构造方法列表------");
        for (Constructor constructor:constructors) {
            String name = constructor.getName();
            StringBuilder sb = new StringBuilder(name);

            sb.append('(');
            Class[] clazzParams = constructor.getParameterTypes();
            for (Class clazzParam:clazzParams) {
                sb.append(clazzParam.getName() + ',');
            }
            if (clazzParams != null && clazzParams.length > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(')');
            System.out.println(sb.toString());
        }
        System.out.println("-------创建实例对象------");
        try {
            // 创建一个具体的 InvocationHandler 实例
            InvocationHandler collectionHandler = new CollectionHandlerTest(new ArrayList());
            // 生成的代理类只有一个有参的构造方法 xxx(InvocationHandler ih), 所以要先获取 Constructor 来然后创建实例
            Collection  collection = (Collection)  clazzProxy.
                    getConstructor(InvocationHandler.class).
                    newInstance(collectionHandler); //将具体的 InvocationHandler 传入构造方法
            // 调用无返回值方法成功
            collection.clear();
            // 调用有返回值方法抛出异常
            // System.out.println(base.collection.size());
            collection.add("ddd");
            System.out.println(collection.size());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    // 动态获取代理类
    public static Object getProxy(final Object target) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 创建一个具体的 InvocationHandler 实例, 这里采用内部类方式
        InvocationHandler handler = (Object proxy, Method method, Object[] args) ->{
            long beginTime = System.currentTimeMillis();
            // 由 Method 对象来执行具体实例对象的方法
            Object obj = method.invoke(target, args);
            long endTime  = System.currentTimeMillis();
            System.out.println(method.getName() + "执行时间为：" + (endTime - beginTime));
            return obj;
        };
        // 动态创建一个实现了某个接口的代理类
        Class clazzProxy = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
        // 生成的代理类只有一个有参的构造方法 xxx(InvocationHandler ih), 所以要先获取 Constructor 来然后创建实例
        Object  object = clazzProxy.getConstructor(InvocationHandler.class).
                newInstance(handler); //将具体的 InvocationHandler 传入构造方法

        return object;
    }

    public static void main(String[] args) {
        //proxyTest();
        try {
            Collection<String> collection = (Collection<String>) getProxy(new ArrayList<String>());
            collection.add("等哈街");
            collection.add("你好");
            System.out.println(collection.toString());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
class CollectionHandlerTest implements InvocationHandler {
    // InvocationHandler invoke() 方法最终执行的具体实例对象,
    private Object target;
    public CollectionHandlerTest(Object target) {
        this.target = target;
    }
    /**
     * 参数：
     * proxy  代理对象
     * method 被代理对象类类型(Class)的 Method 对象
     * args   执行方法的参数
     * 返回值：
     * Object 方法的返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("------开始执行------");
        // 由 Method 对象来执行具体实例对象的方法
        Object obj = method.invoke(this.target, args);
        System.out.println("------执行结束------");
        return obj;
    }
}
