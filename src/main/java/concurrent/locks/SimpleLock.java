package concurrent.locks;

/**
 * 简单的锁接口, 只有阻塞获取锁和解锁的方法.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/9/1 17:26
 */
public interface SimpleLock {

    /**
     * 阻塞获取锁, 直到锁可用.
     */
    void lock();

    /**
     * 释放锁, 当未获取到锁时调用此方法不会抛出错误.
     */
    void unlock();
}
