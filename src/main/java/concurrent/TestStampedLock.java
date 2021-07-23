package concurrent;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * 练习 {@link StampedLock}.
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/7/23 18:48
 */
public class TestStampedLock {

    /**
     * JDK 1.8 版 {@link StampedLock} 下述场景：线程 A 获取写锁，线程 B 获取悲观读锁, 此时其他线程中断线程 B (即 theadB.interrup()) 会导致线程 B 所在 CPU 飙升.
     *  <p>BUG 地址：https://bugs.openjdk.java.net/browse/JDK-8049843</p>
     *  <p>BUG 中文说明：https://ifeve.com/stampedlock-bug-cpu/</p>
     */
    public static void interruptBUGTest() throws InterruptedException {
        final StampedLock lock = new StampedLock();

        Thread t1 = new Thread(() -> {
            // 获取写锁
            lock.writeLock();
            // 阻塞当前线程, 不释放锁.
            LockSupport.park();
        });
        t1.start();
        // 休眠 1 秒保证 t1 获取写锁.
        Thread.sleep(1000);
        Thread t2 = new Thread(() -> {
            // 阻塞在悲观读锁
            lock.readLock();
        });
        t2.start();
        // 休眠 1 秒保证 t2 获取读锁.
        Thread.sleep(1000);
        //中断线程 t2 会导致线程 t2 所在CPU飙升，BUG 已在 JDK 1.9 中解决, 如果使用 1.8 版本则需要特别注意.
        t2.interrupt();
        t2.join();
    }

    public static void main(String[] args) throws InterruptedException {
        interruptBUGTest();
    }
}
