package db.nosql.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * @author <a href="http://doctordeng.vip/">doctordeng</a>
 * @version 1.0
 * @description 练习 Redis Pipeline
 * @since 2018/4/2 20:42
 */
public class JedisPipelineTest extends BaseJedisTest{

    protected static void testPipeline() {
        Jedis jedis = getJedis();
        for (int i = 0; i < 100; i++) {
            Pipeline pipeline = jedis.pipelined();
            for (int j = 0; j < 100; j++) {
                pipeline.hset("hashkey-" + i,"field-" + j, "value-" + j);
            }
            List<Object> list = pipeline.syncAndReturnAll();
            for (int a = 0, len = list.size(); a < len; a++) {
                System.out.println(list.get(a).toString());
            }
        }

        close();
    }

    public static void main(String[] args) {
        testPipeline();
    }
}
