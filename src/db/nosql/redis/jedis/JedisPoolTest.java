package db.nosql.redis.jedis;

import org.junit.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @description 练习 Jedis Pool
 * @since 2018/3/31 16:41
 */
public class JedisPoolTest {
    public static void main(String[] args) {
        // 1. 获取连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();

        // 2. 设置连接池配置信息
        // 设置最大连接数
        config.setMaxTotal(30);
        // 设置最大空闲连接
        config.setMaxIdle(10);

        // 3. 获取连接池
        JedisPool jedisPool = new JedisPool(config, "192.168.32.130", 6379);

        //4. 获得核心对象
        try (Jedis jedis = jedisPool.getResource()) { // JDK 1.7+ 写法
            //通过连接池获得连接
            jedis.set("name", "张三");
            Assert.assertEquals("张三", jedis.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
            //log.info("Jedis 通过连接池连接 Redis 出错");
        }
    }
}
