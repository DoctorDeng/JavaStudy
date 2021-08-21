package foundationEnhancement.classLoaderTest;

import java.util.Date;

/**
 * Description: 测试自定义 ClassLoader 的测试类
 *
 * @author doctordeng
 * @since 2016/11/30 22:05
 */
public class ClassLoaderAttachment extends Date{

    @Override
    public String toString() {
        return "Hello, World";
    }
}
