package concurrent.locks;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * 锁测试基类.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/9/1 19:24
 */
@RunWith(Parameterized.class)
public class LockTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LockTest.class);
    private final int THREAD_NUM = 4;
    private final int COUNT_SIZE = 10000;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[100][0]; // repeat count which you want
    }

    @Test
    public void clhLockTest() throws InterruptedException {
        lockTest(new CLHLock());
    }

    @Test
    public void mcsLockTest() throws InterruptedException {
        lockTest(new MCSLock());
    }

    @Test
    public void aqsMutextLockTest() throws InterruptedException {
        lockTest(new AQSMutexLock());
    }

    private void lockTest(SimpleLock lock) throws InterruptedException {
        Assert.assertNotNull(lock);
        final CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        final Counter counter = new Counter(lock);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                for (int j = 0; j < COUNT_SIZE; j++) {
                    counter.increment();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
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
            // 重复上锁, 测试锁的可重入性.
            //lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
                //lock.unlock();
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
