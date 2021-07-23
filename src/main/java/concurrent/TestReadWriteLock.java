package concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * {@link java.util.concurrent.locks.ReadWriteLock} 练习.
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/7/23 9:33
 */
public class TestReadWriteLock {

    public static void main(String[] args) {
        final Counter counter = new Counter();

        Runnable runnable = () -> {
            while (true) {
                counter.add();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(counter.count());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Counter {

        private final ReadWriteLock lock;

        private final Lock readLock;

        private final Lock writeLock;

        public Counter() {
            super();
            this.lock = new ReentrantReadWriteLock();
            this.readLock = this.lock.readLock();
            this.writeLock = this.lock.writeLock();
        }

        private int count;

        public void add() {
            final Lock writeLock = this.writeLock;
            writeLock.lock();
            try {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
            } finally {
                writeLock.unlock();
            }
        }
        
        public int count() {
            final Lock readLock = this.readLock;
            readLock.lock();
            try {
               return count;
            } finally {
                readLock.unlock();
            }
        }

    }
}
