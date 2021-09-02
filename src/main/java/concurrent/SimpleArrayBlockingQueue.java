package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 Lock 和 Condition 实现的简易阻塞队列.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/7/22 15:43
 */
public class SimpleArrayBlockingQueue<E> {
    // 默认容量: 10.
    private static final int DEFAULT_CAPACITY = 10;
    // 存储元素的数组.
    private final Object[] items;
    // 队列元素数量.
    private int size;
    // 队列头元素下标.
    private int header = 0;
    // 下一个队列尾部元素下标.
    private int tail = 0;

    // 锁.
    private final Lock lock;
    // 队列不为空条件变量.
    private final Condition notEmpty;
    // 队列未满条件变量.
    private final Condition notFull;

    public SimpleArrayBlockingQueue() {
        this(DEFAULT_CAPACITY);
    }

    public SimpleArrayBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than 0, capacity is" + capacity);
        }
        this.items = new Object[capacity];
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull  = lock.newCondition();
    }

    public int size() {
        final Lock lock = this.lock;
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == items.length;
    }

    /**
     * 入队.
     */
    public void enqueue(E element) throws InterruptedException {
        assertElement(element);

        final Lock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (isFull()) {
                notFull.await();
            }
            _enqueue(element);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private void _enqueue(E element) {
        // tail == items.length 表示队列末尾没有空间了
        if (tail == items.length) {
            // tail == items.length - 1 && head==0，表示整个队列都占满了
            if (header == 0) return;
            // 数据搬移
            System.arraycopy(items, header, items, 0, size);
            // 搬移完之后重新更新 head 和 tail
            tail = size;
            header = 0;
        }
        items[tail] = element;
        tail++;
        size++;
    }

    private E _dequeue() {
        if (header == tail) {
            return null;
        }
        final Object element = items[header];
        header++;
        size--;
        return (E) element;
    }

    /**
     * 出队.
     * @return 队列头元素
     */
    public E dequeue() throws InterruptedException {
        final Lock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }
            E e = _dequeue();
            notFull.signalAll();
            return e;
        } finally {
            lock.unlock();
        }
    }

    private void assertElement(E element) {
        if (element == null) {
            throw new NullPointerException("element must not be null");
        }
    }
}
