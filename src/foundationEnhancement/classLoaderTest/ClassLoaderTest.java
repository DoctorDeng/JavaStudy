package foundationEnhancement.classLoaderTest;

/**
 * Description: 练习 Java 类加载器
 *
 * @author Doctor邓
 * @since 2016/11/29 22:28
 */
public class ClassLoaderTest {
    public static void getClassLoader() {
        System.out.println(ClassLoaderTest.class.getClassLoader().getClass().getName());
        /**
         * System 类的类加载器为空, 是因为 System 是由 Bootstrap 加载的
         * 而 Bootstrap 是由 C++ 编写的随 JVM 内核启动的加载器
         */
        System.out.println(System.class.getClassLoader());
    }

    /**
     * 按照层次结构打印加载 ClassLoaderTest 类的所有类加载器的名称
     */
    public static <T> void printClassLoaderName(T t) {
        ClassLoader classLoader = t.getClass().getClassLoader();
        while (classLoader != null) {
            System.out.println("ClassLoader: " + classLoader.getClass().getName());
            classLoader = classLoader.getParent();
        }
    }
    public static void main(String[] args) {
       // printClassLoaderName();
        getClassLoader();
    }
}
