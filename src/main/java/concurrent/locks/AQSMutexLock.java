package concurrent.locks;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 基于 {@link AbstractQueuedSynchronizer} 实现的简易互斥锁.
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/9/8 18:05
 */
public class AQSMutexLock implements SimpleLock {

    private final Sync sync;

    public AQSMutexLock() {
        sync = new Sync();
    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            assertArg(arg);

            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assertArg(arg);
            if (!isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        private void assertArg(int arg) {
            if (arg != 1) {
                throw new IllegalArgumentException();
            }
        }
    }
}
