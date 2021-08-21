package foundationEnhancement;

import java.io.InputStream;

/**
 * Description: 练习 Java 路径
 *
 * @author doctordeng
 * @since 2016/11/26 16:32
 */
public class PathTest {

    public static void pathTest() {
        InputStream is = PathTest.class.getClassLoader().getResourceAsStream("book1.xml");
        //获取根路径
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
    }

    public static void main(String[] args) {
        pathTest();
    }
}
