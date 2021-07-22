package concurrent;

import cache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 基于 {@link java.util.concurrent.locks.ReentrantReadWriteLock} 实现的简易 {@link Cache}.
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/7/22 19:57
 */
public class ReadWriteCache<K, V> implements Cache<K, V> {

    private static final int DEFAULT_CAPACITY = 10;

    private final Map<K, V> data;

    private final ReadWriteLock readWriteLock;

    private final Lock readLock;

    private final Lock writeLock;

    public ReadWriteCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The capacity should be greater than 0, capacity is " + capacity);
        }
        this.data = new HashMap<>(capacity);
        this.readWriteLock = new ReentrantReadWriteLock();
        this.readLock = readWriteLock.readLock();
        this.writeLock = readWriteLock.writeLock();
    }

    public ReadWriteCache() {
        this.data = new HashMap<>(DEFAULT_CAPACITY);
        this.readWriteLock = new ReentrantReadWriteLock();
        this.readLock = readWriteLock.readLock();
        this.writeLock = readWriteLock.writeLock();
    }

    @Override
    public V get(K key) {
        readLock.lock();
        try {
            return data.get(key);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void put(K key, V value) {
        writeLock.lock();
        try {
            data.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean remove(K key) {
        writeLock.lock();
        try {
            if (containsKey(key)) {
                data.remove(key);
                return true;
            } else {
                return false;
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean containsKey(K key) {
        readLock.lock();
        try {
            return data.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }
}
