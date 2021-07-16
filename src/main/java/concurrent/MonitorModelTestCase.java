package concurrent;

/**
 * Java 管程模型验证, 验证 Java 中的管程模型属于下面哪中模型：
 * <p>
 *     1. MESA  模型：线程 B 唤醒线程 A. A 继续等待, B 依旧执行，直到完成.
 *     2. Hasen 模型：线程 B 唤醒线程 A. 唤醒操为最后才可以执行的操作.
 *     3. Hoare 模型：线程 B 唤醒线程 A. A 开始执行, B 进行等待.
 * </p>
 * 测试结果：线程 B 唤醒线程 A 后，B 直到执行完毕线程 A 才能够执行因此 Java 遵守 MESA 模型.
 * @version 1.0.0
 * @date 2021/7/16 18:55
 */
public class MonitorModelTestCase {

    /**
     * 打印结果：
     * this is Runnable A
     * this is Runnable B
     * this is Runnable B
     * this is Runnable A
     * this is Runnable B
     * this is Runnable B
     * this is Runnable A
     * ......
     */
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Runnable a = () -> {
            while (true) synchronized (lock) {
                System.out.println("this is Runnable A");
                try {
                    lock.notifyAll();
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Runnable b = () -> {
            while (true) synchronized (lock) {
                lock.notifyAll();
                try {
                    for (int i = 0; i < 2; i++) {
                        Thread.sleep(1000);
                        System.out.println("this is Runnable B");
                    }
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread threadA = new Thread(a);
        Thread threadB = new Thread(b);
        threadA.start();
        threadB.start();
    }

}
