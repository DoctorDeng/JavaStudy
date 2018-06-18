package db.nosql.redis.jedis.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="http://doctordeng.vip/">DoctorDeng</a>
 * @version 1.0
 * @description JedisCluster 工厂
 * @since 2018/6/18 16:04
 */
public class JedisClusterFactory {
    private static final Logger logger = LoggerFactory.getLogger(JedisClusterFactory.class);
    private JedisCluster jedisCluster;
    private List<String> hostPortList;
    /**
     * 单位: ms(毫秒)
     */
    private int timeout;

    public void init() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        for (int i = 0, len = hostPortList.size(); i < len; i++) {
            String[] arr = hostPortList.get(i).split(":");
            if (arr.length == 2) {
                HostAndPort hostAndPort = new HostAndPort(arr[0], Integer.parseInt(arr[1]));
                hostAndPorts.add(hostAndPort);
            }
        }

        if (timeout < 1) {
            timeout = 2000;
        }
        try {
            jedisCluster = new JedisCluster(hostAndPorts, timeout, jedisPoolConfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void destroy() {
        if (jedisCluster != null) {
            try {
                jedisCluster.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setHostPortList(List<String> hostPortList) {
        this.hostPortList = hostPortList;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
