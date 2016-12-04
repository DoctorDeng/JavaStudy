package foundationEnhancement.proxy;


import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Description: 练习 JDK 的动态代理相关类之Proxy
 *
 * @author Doctor邓
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
        Method[] methods = clazzProxy.getMethods();
        System.out.println("------方法列表------");
        for (Method method:methods) {
            String name = method.getName();
            StringBuilder sb = new StringBuilder(name);

            sb.append('(');
            Class[] clazzParams = method.getParameterTypes();
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
            InvocationHandler collectionHandler = new CollectionHandlerTest();
            // 生成的代理类只有一个有参的构造方法 xxx(InvocationHandler ih), 所以要先获取 Constructor 来然后创建实例
            Collection  collection = (Collection)  clazzProxy.
                    getConstructor(InvocationHandler.class).
                    newInstance(collectionHandler); //将具体的 InvocationHandler 传入构造方法
            System.out.println(collection);
            // 调用无返回值方法成功            collection.clear();
            // 调用有返回值方法抛出异常
           // System.out.println(collection.size());
            //collection.add("ddd");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        proxyTest();
    }
}
class CollectionHandlerTest implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       /* System.out.println("正在添加");
        method.invoke(args);
        System.out.println("添加完成");*/
       ArrayList arrayList = new ArrayList();
        return null;
    }
}
