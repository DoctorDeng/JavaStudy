package concurrent;

import cache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

/**
 * 使用 {@link java.util.concurrent.locks.StampedLock} 实现的简易 {@link Cache}.
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/7/23 19:25
 */
public class StampedLockCache<K, V> implements Cache<K, V> {

    private final StampedLock lock;

    private static final int DEFAULT_CAPACITY = 10;

    private final Map<K, V> data;

    public StampedLockCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The capacity should be greater than 0, capacity is " + capacity);
        }
        this.data = new HashMap<>(capacity);
        this.lock = new StampedLock();
    }

    public StampedLockCache() {
        this.data = new HashMap<>(DEFAULT_CAPACITY);
        this.lock = new StampedLock();
    }


    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        long stamp = lock.tryOptimisticRead();
        final V value = data.get(key);
        if (!lock.validate(stamp)) {
            try {
                stamp = lock.readLock();
                return data.get(key);
            } finally {
                lock.unlockRead(stamp);
            }
        } else {
            return value;
        }
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        long stamp = lock.writeLock();
        try {
            data.put(key, value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    @Override
    public boolean remove(K key) {
        if (key == null) {
            return false;
        }
        long stamp = lock.writeLock();
        try {
            if (data.containsKey(key)) {
                data.remove(key);
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        long stamp = lock.tryOptimisticRead();
        final boolean contains = data.containsKey(key);
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                return data.containsKey(key);
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return contains;
    }
}
