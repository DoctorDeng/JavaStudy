package concurrent;

import java.util.concurrent.Semaphore;

/**
 * 基于 {@link java.util.concurrent.Semaphore} 实现的互斥锁.
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/7/22 18:58
 */
public class SemaphoreLock {

    private final Semaphore semaphore;

    public SemaphoreLock() {
        this.semaphore = new Semaphore(1);
    }

    public void lock() throws InterruptedException {
        this.semaphore.acquire();
    }

    public void unlock() {
        this.semaphore.release();
    }

}
