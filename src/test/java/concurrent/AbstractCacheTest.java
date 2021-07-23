package concurrent;

import cache.Cache;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * @author DoctorDeng
 * @version 1.0.0
 * @date 2021/7/23 19:42
 */
public abstract class AbstractCacheTest {

    public abstract Cache<String, Integer> getCacheInstance();

    public Cache<String, Integer> getCache() {
        final Cache<String, Integer> cache = getCacheInstance();
        for (int i = 0; i < 3; i++) {
            cache.put(String.valueOf(i), i);
        }
        return cache;
    }

    public void cacheTest() {
        final Cache<String, Integer> cache = getCache();

        Runnable readRunnable = () -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("get cache, key is:" + i + " value is:" + cache.get(String.valueOf(i)));
                }
            }
        };
        Thread thread1 = new Thread(readRunnable);
        thread1.start();
        for (int i = 0; i < 100; i++) {
            int key = RandomUtils.nextInt(0, 3);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int value = RandomUtils.nextInt(100, 2000);
            cache.put(String.valueOf(key), value);
            System.out.println("put cache, key is:"  + key + " value is:" + value);
        }
    }
}
