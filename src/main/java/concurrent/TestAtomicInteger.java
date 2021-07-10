package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {
    private int value;

    public TestAtomicInteger(int value) {
        this.value = value;
    }

    public synchronized int increase() {
        return value++;  //若要线程安全执行 value++ 需要加锁
    }

    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        TestAtomicInteger test = new TestAtomicInteger(0);
        for (int i = 0; i < 1000000; i++) {
            test.increase();
        }
        long end = System.currentTimeMillis();
        System.out.println("time elapse:" + (end - start));

        long start1 = System.currentTimeMillis();
        AtomicInteger atomic = new AtomicInteger(0);

        for (int i = 0; i < 1000000; i++) {
            atomic.incrementAndGet();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("time elapse:" + (end1 - start1));
    }
}