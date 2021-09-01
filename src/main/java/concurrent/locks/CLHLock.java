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
     * CLH 队列队尾节点, 初始时 tail 执行一个空节点.
     */
    volatile Node tail;

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
            Node t = tail;
            // 1. 初始化 tail
            if (t == null) {
                tryInitializeTail();
            }
            Node pred = (node == null ? null : node.prev);
            // 2. 节点未创建时创建节点
            if (node == null) {
                node = new Node();
            }
            // 3. 如果前驱为 null 说明节点未入队.
            else if (pred == null) {
                node.setPrevRelaxed(t);
                if (!casTail(tail, node)) {
                    node.setPrevRelaxed(null);
                } else {
                    nodeLocal.set(node);
                }
            }
            // 自旋, 当前驱节点状态为 RELEASE 时，获取到锁.
            else {
                while (pred.status != RELEASE);
                break;
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

    final boolean casTail(CLHLock.Node e, CLHLock.Node v) {
        return U.compareAndSwapObject(this, TAIL, e, v);
    }
    // 节点状态, RELEASE 表示已释放锁.
    private static final int RELEASE = 1;

    static class Node {
        volatile int status;

        volatile Node prev;

//        final boolean casPrev(CLHLock.Node e, CLHLock.Node v) {
//            return U.compareAndSwapObject(this, PREV, e, v);
//        }

        final void setPrevRelaxed(Node prev) {
            U.putObjectVolatile(this, PREV, prev);
        }
        // 将锁状态标记未已释放.
        final void setStatusRelease() {
            U.putIntVolatile(this, STATUS, RELEASE);
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
//    private static final long HEAD = U.objectFieldOffset(CLHLock.class, "head");

    static {
        try {
            Class<?> lock = CLHLock.class;
            TAIL = U.objectFieldOffset(lock.getDeclaredField("tail"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

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
