package concurrent;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SemaphoreLockTest {

    @Test
    public void lock() throws InterruptedException {
        int count = 1000000;
        final Couter couter = new Couter();
        Runnable runnable = () -> {
            for (int i = 0; i < count; i++) {
                couter.add();
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        Assert.assertEquals(couter.count(), count * 2);
    }

    static class Couter {
        private int count = 0;

        private final SemaphoreLock lock;

        public Couter() {
            this.lock = new SemaphoreLock();
        }

        public void add() {
            try {
                lock.lock();
                count++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        public int count() {
            try {
                lock.lock();
                return count;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}