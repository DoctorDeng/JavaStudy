package db.nosql.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author <a href="http://doctordeng.vip/">doctordeng</a>
 * @version 1.0
 * @description 所有练习 Jedis 基类, 用于获取 Jedis 连接
 * @since 2018/4/2 20:49
 */
public class BaseJedisTest {
    // 1. 获取连接池配置对象
    private static final JedisPoolConfig config = new JedisPoolConfig();

    // 3. 获取连接池
    private static JedisPool jedisPool = null;
    private static final ThreadLocal<Jedis> THREAD_LOCAL = new ThreadLocal<>();

    static {
        // 2. 设置连接池配置信息
        // 设置最大连接数
        config.setMaxTotal(30);
        // 设置最大空闲连接
        config.setMaxIdle(10);
        jedisPool = new JedisPool(config, "192.168.1.27", 8000);
    }

    protected static Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        THREAD_LOCAL.set(jedis);
        return jedis;
    }

    protected static void close() {
        if(THREAD_LOCAL.get() != null ) {
            THREAD_LOCAL.get().close();
        }
        THREAD_LOCAL.remove();
    }
}
