package cache;

/**
 * 缓存接口.
 * @author <a href="http://doctordeng.vip">DoctorDeng</a>
 * @date 2021/1/13 18:38
 * @since 1.0.0
 */
public interface Cache<K, V> {

    /**
     * 获取指定 key 对应的缓存对象.
     * @param key 缓存 key
     * @return 缓存对象
     */
    V get(K key);

    /**
     * 放入缓存.
     * @param key 缓存 key
     * @param value 缓存对象
     */
    void put(K key, V value);

    /**
     * 移除指定 key 对应的缓存.
     * @param key 缓存 key
     * @return true 移除成功, false 移除失败
     */
    boolean remove(K key);

    /**
     * 判断指定 key 的缓存是否存在.
     * @param key 缓存 key
     * @return true 存在, false 不存在
     */
    boolean containsKey(K key);

    interface CacheEntry<K, V> {
        /**
         * 获取缓存 Key.
         * @return 缓存 key
         */
        K getKey();

        /**
         * 缓存缓存对象.
         * @return 缓存对象
         */
        V getValue();
    }
}
