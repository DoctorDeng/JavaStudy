package concurrent.locks;

import org.junit.Assert;
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
public class LockTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LockTest.class);
    private final int THREAD_NUM = 4;
    private final int COUNT_SIZE = 10000;

    public void lockTest(SimpleLock lock) throws InterruptedException {
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

        //AtomicInteger count2 = new AtomicInteger(0);

        final SimpleLock lock;

        //final Lock lock2 = new ReentrantLock();

        public Counter(SimpleLock lock) {
            this.lock = lock;
        }

        public void increment() {
            lock.lock();
            //lock2.lock();
            try {
//                if (count2.get() != count) {
//                    LOGGER.info("expect {}, actual {}", count2.get(), count);
//                }
//                count2.getAndIncrement();
                count++;
            } finally {
                lock.unlock();
                //lock2.unlock();
            }
        }

        public int getCount() {
            lock.lock();
            //lock2.lock();
            try {
                return count;
            } finally {
                lock.unlock();
                //lock2.unlock();
            }
        }
    }
}
