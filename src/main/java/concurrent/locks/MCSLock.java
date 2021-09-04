package concurrent.locks;

import sun.misc.Unsafe;

/**
 * MCS 锁实现, 支持重入功能.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/9/2 11:16
 */
public class MCSLock implements SimpleLock {

    /**
     * 队列队尾节点, 当锁被其他线程占用时线程对应节点会被添加到队列尾部进行自旋等待.
     */
    private volatile Node tail;

    /**
     * 保存线程对应节点.
     */
    private final ThreadLocal<Node> nodeLocal;

    /**
     * 独占锁的线程.
     */
    private Thread exclusiveOwnerThread;

    /**
     * 获取锁成功的次数, 占有锁的线程每次获取锁时该值会 +1.
     * <p> 在释放锁时如果 num > 0 则只会将 num 值 -1, 当 num == 0 是才会进行真正释放锁的操作.
     */
    private volatile int num;

    public MCSLock() {
        super();
        nodeLocal = ThreadLocal.withInitial(Node::new);
    }

    @Override
    public void lock() {
        Thread current = Thread.currentThread();
        // 1. 当前线程已获持有锁时将 num + 1.
        if (current == exclusiveOwnerThread) {
            int c = num + 1;
            if (c < 0) {
                throw new Error("Maximum lock count exceeded");
            }
            num = c;
            return;
        }
        Node node = nodeLocal.get();
        // 2. 将节点添加到队列尾部.
        Node pred = getAndSetTail(node);
        // 3. 如果队列不为空(即 tail != null) 则将原 tail 指向该节点并自旋等待.
        if (pred != null) {
            // 两行代码不能互换否则会导致某些情况下所有线程都获取不到锁.
            node.locked();
            pred.setNextRelaxed(node);
            while(node.isLocked())  {
                // 自旋期间主动释放线程可以减少高竞争情况下 CPU 使用率.
                Thread.yield();
            }
        }
        // 4. 获取到锁后将锁的持有者设置为当前线程并将 num 设置为 1
        exclusiveOwnerThread = current;
        num = 1;
    }

    @Override
    public void unlock() {
        Thread current = Thread.currentThread();
        // 只有锁的持有者才能释放锁.
        if (current == exclusiveOwnerThread) {
            int c = num - 1;
            boolean free = (c == 0);
            // 当 num == 0 时才进行真正释放锁的操作.
            if (free) {
                // 重置锁的持有线程与 num.
                exclusiveOwnerThread = null;
                num = c;
                Node node = nodeLocal.get();
                // 当队列中没有其他线程等待获取锁时，尝试使用 cas 操作将  tail 指向 null, 如果失败则表示期间有新的线程在入队.
                // 由于入队操作不是原子操作, 因此需要使用 while 循环来等待新线程入队操作结束, 然后再开始进行唤醒后续线程的操作.
                while (node.next == null) {
                    if (casTail(node, null)) {
                        return;
                    } else {
                        Thread.yield();
                    }
                }
                // 当 node 有后驱节点时直接唤醒后驱节点.
                node.next.unLocked();
                node.next = null;
                return;
            }
            // num 不为 0 时将 num 值 -1.
            num = c;
        }
    }

    final boolean casTail(Node e, Node v) {
        return U.compareAndSwapObject(this, TAIL, e, v);
    }

    final Node getAndSetTail(Node n) {
        return (Node) U.getAndSetObject(this, TAIL, n);
    }

    static class Node {

        /**
         * 是否已上锁, false 时该节点对应线程是获取到锁的线程.
         */
        volatile boolean locked;

        /**
         * 节点后驱节点, 当节点获取到锁后, 释放锁时会通知下一个节点获取锁.
         */
        volatile Node next;

        final void setNextRelaxed(Node next) {
            this.next = next;
        }

        final boolean isLocked() {
            return this.locked;
        }

        final void locked() {
            this.locked = true;
        }

        final void unLocked() {
            this.locked = false;
        }

    }

    // Unsafe
    private static final Unsafe U = getUnsafe();
    private static final long TAIL;

    static {
        try {
            Class<?> lock = MCSLock.class;
            TAIL = U.objectFieldOffset(lock.getDeclaredField("tail"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    // Copy form Guava.
    private static sun.misc.Unsafe getUnsafe() {
        try {
            return sun.misc.Unsafe.getUnsafe();
        } catch (SecurityException tryReflectionInstead) {
        }
        try {
            return java.security.AccessController.doPrivileged(
                    new java.security.PrivilegedExceptionAction<sun.misc.Unsafe>() {
                        @Override
                        public sun.misc.Unsafe run() throws Exception {
                            Class<sun.misc.Unsafe> k = sun.misc.Unsafe.class;
                            for (java.lang.reflect.Field f : k.getDeclaredFields()) {
                                f.setAccessible(true);
                                Object x = f.get(null);
                                if (k.isInstance(x)) return k.cast(x);
                            }
                            throw new NoSuchFieldError("the Unsafe");
                        }
                    });
        } catch (java.security.PrivilegedActionException e) {
            throw new RuntimeException("Could not initialize intrinsics", e.getCause());
        }
    }
}
