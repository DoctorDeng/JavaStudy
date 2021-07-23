package concurrent;

import cache.Cache;
import org.junit.Test;

public class StampedLockCacheTest extends AbstractCacheTest {

    @Override
    public Cache<String, Integer> getCacheInstance() {
        return new StampedLockCache<>();
    }

    @Test
    public void cacheTest() {
        super.cacheTest();
    }
}