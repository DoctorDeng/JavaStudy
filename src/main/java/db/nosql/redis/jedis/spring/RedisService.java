package db.nosql.redis.jedis.spring;

/**
 * @author <a href="http://doctordeng.vip/">doctordeng</a>
 * @version 1.0
 * @description
 * @since 2018/6/18 16:03
 */
public interface RedisService {
    String get(String key);
    String set(String key, String value);
}
