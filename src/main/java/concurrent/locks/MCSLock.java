package concurrent.locks;

import sun.misc.Unsafe;

/**
 * MCS 锁实现.
 *
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/9/2 11:16
 */
public class MCSLock implements SimpleLock {

    /**
     * 队列队尾节点.
     */
    volatile Node tail;

    /**
     * 保存线程对应节点.
     */
    final ThreadLocal<Node> nodeLocal;

    public MCSLock() {
        super();
        nodeLocal = ThreadLocal.withInitial(Node::new);
    }

    @Override
    public void lock() {
        Node node = nodeLocal.get();

//        for (;;) {
//            Node t = tail;
//            Node next = (node == null ? null : node.next);
//
//            if (next == null && node != t) {
//                if (casTail(t, node)) {
//                    // 两行代码不能互换否则会导致某些情况下所有线程都获取不到锁.
//                    node.locked();
//                    if (t != null) {
//                        t.setNextRelaxed(node);
//                    }
//                }
//            } else {
//                while(node.isLocked())  {
//                    Thread.yield();
//                }
//                return;
//            }
        Node pred = getAndSetTail(node);
        if (pred != null) {
            // 两行代码不能互换否则会导致某些情况下所有线程都获取不到锁.
            node.locked();
            pred.setNextRelaxed(node);
            while(node.isLocked())  {
                Thread.yield();
            }
        }
    }

    @Override
    public void unlock() {
        Node node = nodeLocal.get();
        while (node.next == null) {
            if (casTail(node, null)) {
                return;
            } else {
                Thread.yield();
            }
        }
        node.next.unLocked();
        node.next = null;
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

//        private static final long LOCKED;
//        private static final long NEXT;
//
//        static {
//            try {
//                Class<?> node = MCSLock.Node.class;
//                LOCKED = U.objectFieldOffset(node.getDeclaredField("locked"));
//                NEXT = U.objectFieldOffset(node.getDeclaredField("next"));
//            } catch (Exception e) {
//                throw new Error(e);
//            }
//        }
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
