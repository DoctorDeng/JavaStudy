package db.nosql.redis.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @description 练习 JedisCluster 使用
 * @since 2018/6/14 21:05
 */
public class JedisClusterTest {
    private static JedisCluster cluster;
    static {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.31.253", 8000));
        nodes.add(new HostAndPort("192.168.31.253", 8001));
        nodes.add(new HostAndPort("192.168.31.253", 8002));
        nodes.add(new HostAndPort("192.168.31.253", 8003));
        nodes.add(new HostAndPort("192.168.31.253", 8004));
        nodes.add(new HostAndPort("192.168.31.253", 8005));
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        //config.setMaxTotal(30);
        // 设置最大空闲连接
        //config.setMaxIdle(10);
        cluster = new JedisCluster(nodes, config);
    }

    private static void test() {
        cluster.set("hello", "world");
        System.out.println(cluster.get("hello"));
    }

    public static void main(String[] args) {
        test();
    }
}
