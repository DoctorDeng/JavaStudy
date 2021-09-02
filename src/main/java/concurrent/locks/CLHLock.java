package concurrent.locks;

import sun.misc.Unsafe;

/**
 * CLH 锁实现.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/9/1 17:26
 */
public class CLHLock implements SimpleLock {

    /**
     * CLH 队列队尾节点, 初始时 tail 是一个空节点.
     */
    volatile Node tail;
    /**
     * 保存线程对应 CLH 节点.
      */
    final ThreadLocal<Node> nodeLocal;

    public CLHLock() {
        super();
        nodeLocal = new ThreadLocal<>();
    }

    private void tryInitializeTail() {
        Node h = new Node();
        h.setStatusRelease();
        casTail(null, h);
    }

    @Override
    public void lock() {
        Node node = nodeLocal.get();
        for (;;) {
            Node pred = (node == null ? null : node.prev);
            // 1. 节点未创建时创建节点.
            if (node == null) {
                node = new Node();
            }
            // 2. 如果前驱为 null 说明节点未入队，尝试入队操作.
            else if (pred == null) {
                Node t = tail;
                node.setPrevRelaxed(t);
                // 2.1 如果 tail 未初始化, 初始化 tail.
                if (t == null) {
                    tryInitializeTail();
                }
                // 2.2 尝试入队，入队失败清空前驱.
                else if (!casTail(t, node)) {
                    node.setPrevRelaxed(null);
                }
                // 2.3 入队成功将其放入 ThreadLocal 中.
                else {
                    nodeLocal.set(node);
                }
            }
            // 3. 自旋, 直到前驱节点状态为 RELEASE 时，获取到锁.
            else {
                while (!pred.isRelease()) {
                    Thread.yield();
                }
                return;
            }
        }
    }

    @Override
    public void unlock() {
        Node node = nodeLocal.get();
        if (node != null) {
            node.setPrevRelaxed(null);
            node.setStatusRelease();
            nodeLocal.remove();
        }
    }

    /**
     * 原子更新 tail, 新的节点通过调用该方法入队.
     */
    final boolean casTail(CLHLock.Node e, CLHLock.Node v) {
        return U.compareAndSwapObject(this, TAIL, e, v);
    }

    static class Node {
        // 节点状态, RELEASE 表示已释放锁.
        private static final int RELEASE = 1;

        volatile int status;

        volatile Node prev;

        final void setPrevRelaxed(Node prev) {
            U.putObjectVolatile(this, PREV, prev);
        }

        // 将状态标记未已释放.
        final void setStatusRelease() {
            U.putIntVolatile(this, STATUS, RELEASE);
        }

        final boolean isRelease() {
            return status == RELEASE;
        }

        private static final long STATUS;
        private static final long PREV;

        static {
            try {
                Class<?> node = CLHLock.Node.class;
                STATUS = U.objectFieldOffset(node.getDeclaredField("status"));
                PREV = U.objectFieldOffset(node.getDeclaredField("prev"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    // Unsafe
    private static final Unsafe U = getUnsafe();
    private static final long TAIL;

    static {
        try {
            Class<?> lock = CLHLock.class;
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
