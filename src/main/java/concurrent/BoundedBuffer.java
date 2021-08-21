package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓冲区示例:
 * 假定有一个绑定的缓冲区，它支持 put 和 take 方法。
 * 如果试图在空的缓冲区上执行 take操作，则在某一个项变得可用之前，线程将一直阻塞；
 * 如果试图在满的缓冲区上执行 put 操作，则在有空间变得可用之前，线程将一直阻塞。
 * 我们喜欢在单独的等待 set 中保存 put 线程和 take 线程，这样就可以在缓冲区中的项或空间变得可用时利用最佳规划，
 * 一次只通知一个线程。 可以使用两个Condition 实例来做到这一点。
 *
 * @author doctordeng
 */
public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    /**
     * 使用两个 Condition 原因:
     * 一个 Condition 只能通知一类线程, 例如: 取的线程使用 Condition.signal() 只能唤醒其他被堵塞取的线程
     * 而取得线程线程实际应该通知放的线程, 所以要使用两个 condition
     */
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            //当缓冲区慢时, 等待
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length)
                putptr = 0;
            ++count;
            //唤醒取的线程
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length)
                takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
