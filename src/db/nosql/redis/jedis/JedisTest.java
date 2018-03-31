package db.nosql.redis.jedis;

import redis.clients.jedis.Jedis;

/**
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @description 练习 Jedis 与 Redis 交互
 * @since 2018/3/31 16:14
 */
public class JedisTest {
    // 1. 设置 IP 地址和端口
    Jedis jedis = new Jedis("192.168.32.130",6379);

    private void testSet() {
        jedis.set("key1", "value1");
        System.out.println("key1 value:" + jedis.get("key1"));
    }

    public static void main(String[] args) {
        JedisTest jedisTest = new JedisTest();
        jedisTest.testSet();
    }
}
