package concurrent.locks;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 锁测试基类.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/9/1 19:24
 */
public class LockTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LockTest.class);
    private int THREAD_NUM = 4;
    private int COUNT_SIZE = 10000;

    public void lockTest(SimpleLock lock) throws InterruptedException {
        Assert.assertNotNull(lock);
        final Counter counter = new Counter(lock);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                for (int j = 0; j < COUNT_SIZE; j++) {
                    counter.increment();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(8);
        Assert.assertEquals(THREAD_NUM * COUNT_SIZE, counter.getCount());
    }

    static class Counter {

        int count = 0;

        final SimpleLock lock;

        public Counter(SimpleLock lock) {
            this.lock = lock;
        }

        public void increment() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }

        public int getCount() {
            lock.lock();
            try {
                return count;
            } finally {
                lock.unlock();
            }
        }
    }
}
