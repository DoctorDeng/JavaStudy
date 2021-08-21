package db.nosql.redis.jedis.spring.impl;

import db.nosql.redis.jedis.spring.RedisService;
import redis.clients.jedis.JedisCluster;

/**
 * @author <a href="http://doctordeng.vip/">doctordeng</a>
 * @version 1.0
 * @description
 * @since 2018/6/18 16:47
 */
public class RedisClusterServiceImpl implements RedisService {
    //@Resource(name = "jedisCluster")
    private JedisCluster jedisCluster;

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }
}
