package concurrent;

import cache.Cache;
import org.junit.Test;

public class ReadWriteCacheTest extends AbstractCacheTest {

    @Override
    public Cache<String, Integer> getCacheInstance() {
        return new ReadWriteCache<>();
    }

    @Test
    public void cacheTest() {
        super.cacheTest();
    }

}