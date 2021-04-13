package db.nosql.redis.jedis;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="http://doctordeng.vip/">Doctor邓</a>
 * @version 1.0
 * @description 练习 JedisCluster 使用
 * @since 2018/6/14 21:05
 */
public class JedisClusterTest {
    private static final Logger log = LoggerFactory.getLogger(JedisClusterTest.class);
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

    private static void simpleTest() {
        cluster.set("hello", "world");
        System.out.println(cluster.get("hello"));
    }

    /**
     * 练习 Redis Cluster 多节点命令
     */
    private static void multiClusterNodeCommandTest() {
        // 获取所有节点
        Map<String, JedisPool> jedisPoolMap = cluster.getClusterNodes();
        for (Map.Entry<String, JedisPool> entry : jedisPoolMap.entrySet()) {
            // 获取每个节点的 Jedis 连接
            Jedis jedis = entry.getValue().getResource();
            if (isMaster(jedis)) {
                // do something
                // ......

                // close: 需要手动关闭
                jedis.close();
            }
        }
    }

    private static boolean isMaster(@NonNull Jedis jedis) {
        Assert.notNull(jedis);
        String info = jedis.info("replication");
        if (info != null && info.contains("role:master")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Redis Cluster 故障转移测试
     */
    private static void failoverTest() {
        int i = 0;
        while (true) {
            String key = "key" + i;
            String value = "value" + i;
            try {
                cluster.set(key, value);
                log.info(cluster.get(key));
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                //e.printStackTrace();
                log.error(e.getMessage(), e);
            }
            i++;
        }
    }

    public static void main(String[] args) {
        //simpleTest();
        //multiClusterNodeCommandTest();
        failoverTest();
    }
}
