package db.nosql.redis.jedis;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="http://doctordeng.vip/">DoctorDeng</a>
 * @version 1.0
 * @description 练习 Jedis 连接 Redis Sentinel
 * @since 2018/5/17 22:39
 */
public class JedisSentinelTest {
    private static org.apache.log4j.Logger logger = Logger.getLogger(JedisSentinelTest.class);
    private static final JedisPoolConfig config;
    private static final Set<String> sentinels = new HashSet<>();
    private static final String masterName = "mymaster";

    static {
        config = new JedisPoolConfig();
        config.setMaxTotal(30);
        // 设置最大空闲连接
        config.setMaxIdle(10);
        config.setMaxWaitMillis(10000);
        sentinels.add("192.168.1.27:26379");
        sentinels.add("192.168.1.27:26380");
        sentinels.add("192.168.1.27:26381");
    }
    private static JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, config );

    public static void main(String[] args) {
        int counter = 0;
        while (true) {
            counter++;
            try (Jedis jedis = jedisSentinelPool.getResource()) {
                int random = new Random().nextInt(100000);
                String key = "k-" + random;
                String value = "v-" + random;
                jedis.set(key, value);
                if (counter % 100 == 0) {
                    logger.info(key + " value is " + jedis.get(key));
                }
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
                logger.info(e);
            } finally {
            }
        }
    }
}
