package datastructureAndalgorithm.practice;

import datastructureAndalgorithm.linkedlist.SortSinglyLinkedList;

import java.util.Iterator;

/**
 * 基于 LRU 算法进行缓存淘汰的 {@link Cache} 实现
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/1/13 19:08
 * @since 1.0.0
 */
public class LRUCache<K, V> implements Cache<K, V> {
    /**
     * 缓存链表
     */
    private final SortSinglyLinkedList<LRUCacheEntry<K, V>> cacheList;

    /**
     * 默认缓存容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 缓存容量
     */
    private final int capacity;

    public LRUCache() {
        super();
        this.capacity = DEFAULT_CAPACITY;
        cacheList = new SortSinglyLinkedList<>(false);
    }

    public LRUCache(int capacity) {
        super();
        if (capacity <= 0) {
            throw new IllegalArgumentException("cache capacity must be greater than or equal to 0");
        }
        this.capacity = capacity;
        cacheList = new SortSinglyLinkedList<>(false);
    }

    @Override
    public V get(K key) {
        LRUCacheEntry<K, V> cacheEntry = get(key ,true);
        return cacheEntry == null ? null : cacheEntry.getValue();
    }

    /**
     * 内部获取指定 key 对应的缓存对象
     * @param key 缓存 key
     * @param accessRecord 是否进行缓存访问记录
     * @return 缓存对象
     */
    private LRUCacheEntry<K, V> get(K key, boolean accessRecord) {
        if (key == null || cacheList.isEmpty()) {
            return null;
        }

        Iterator<LRUCacheEntry<K, V>> iterator = cacheList.iterator();
        while (iterator.hasNext()) {
            LRUCacheEntry<K, V> cacheEntry = iterator.next();
            if (key.equals(cacheEntry.getKey())) {
                if (accessRecord) {
                    iterator.remove();
                    cacheEntry.refreshLatestAccessTime();
                    cacheList.add(cacheEntry);
                }
                return cacheEntry;
            }
        }

        return null;
    }


    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("cache key must not be null");
        }

        Iterator<LRUCacheEntry<K, V>> iterator = cacheList.iterator();
        while (iterator.hasNext()) {
            LRUCacheEntry<K, V> cacheEntry = iterator.next();
            if (key.equals(cacheEntry.getKey())) {
                // 如果新的值和旧有的值相同则不进行任何处理
                if (cacheEntry.getValue() != null
                        && cacheEntry.getValue().equals(value)) {
                    return;
                } else {
                    // 当缓存中已存在指定 key 对应的缓存时刷新缓存
                    iterator.remove();
                    cacheEntry.refreshLatestAccessTime();
                    cacheList.add(cacheEntry);
                    return;
                }
            }
            // 最后一个节点时判断缓存容量是否已达上限, 如果已达上限则删除最后一个节点
            else if (!iterator.hasNext()) {
                if (cacheList.size() >= capacity) {
                    iterator.remove();
                }
            }
        }

        cacheList.add(new LRUCacheEntry<>(key, value));
        System.out.println(cacheList.size());
    }

    @Override
    public boolean remove(K key) {
        if (key == null || cacheList.isEmpty()) {
            return false;
        }

        Iterator<LRUCacheEntry<K, V>> iterator = cacheList.iterator();
        while (iterator.hasNext()) {
            LRUCacheEntry<K, V> cacheEntry = iterator.next();
            if (key.equals(cacheEntry.getKey())) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key, false) != null;
    }

    @Override
    public String toString() {
        return cacheList.toString();
    }

    static class LRUCacheEntry<K, V> implements CacheEntry<K, V>, Comparable<LRUCacheEntry<K, V>> {
        /**
         * 缓存 key
         */
        private K key;

        /**
         * 缓存对象
         */
        private V value;

        /**
         * 最后一次访问时间
         */
        private long lastAccessTime;

        public LRUCacheEntry(K key, V value) {
            this.key = key;
            this.value = value;
            this.lastAccessTime = System.currentTimeMillis();
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void refreshLatestAccessTime() {
            this.lastAccessTime = System.currentTimeMillis();
        }

        @Override
        public int compareTo(LRUCacheEntry<K, V> o) {
            if (o == null) {
                return 1;
            }
            if (this.lastAccessTime == o.lastAccessTime) {
                return 0;
            }
            return this.lastAccessTime - o.lastAccessTime > 0 ? 1 : -1;
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    ", lastAccessTime=" + lastAccessTime +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        basicTest();
    }

    private static void basicTest() throws InterruptedException {
        Cache<String, Integer> cache = new LRUCache<>(5);
        cache.put("1", 1);
        Thread.sleep(100);
        cache.put("2", 2);
        System.out.println(cache);
        Thread.sleep(100);
        cache.get("1");
        Thread.sleep(100);
        cache.put("3", 3);
        Thread.sleep(100);
        cache.put("4", 4);
        Thread.sleep(100);
        cache.put("5", 5);
        Thread.sleep(100);
        cache.put("6", 6);
        System.out.println(cache);
    }
}
