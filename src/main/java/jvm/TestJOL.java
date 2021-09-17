package jvm;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java Object Layout (JOL) 工具包使用练习.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/9/16 17:45
 */
public class TestJOL {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestJOL.class);

    /**
     * 验证对象头（Object Header）中的 mark word 与哈希码关系.
     * <p>结论：
     * <p>1. 当对象没有重写 hashCode() 方法时当第一次调用对象 hashCode() 方法，
     * 对象的哈希码会被计算出并被缓存到 mark word 中.
     * <p>2. 当对象重新 hashCode() 方法后对象的哈希码会被实时计算出且不会被缓存到 mark word 中.
     */
    private static void hashCodeInMarkWordTest() {
        Object object =new Object();
        ClassLayout userLayout = ClassLayout.parseInstance(object);
        System.out.println(userLayout.toPrintable());
        System.out.println(object.hashCode());
        System.out.println(userLayout.toPrintable());
    }

    private static final Object object = new Object();

    private void markWordAndSynchronizedTest() {
        synchronized (object){
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        hashCodeInMarkWordTest();
    }
}
