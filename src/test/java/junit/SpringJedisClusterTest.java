package junit;

import db.nosql.redis.jedis.spring.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jedis-spring/jedis-test-spring.xml")
public class SpringJedisClusterTest {
    @Resource(name = "redisClusterService")
    private RedisService redisService;
    @Test
    public void test() {
        org.junit.Assert.assertNotNull(redisService);
        redisService.set("hello", "world");
        System.out.println(redisService.get("hello"));
        org.junit.Assert.assertEquals("world", redisService.get("hello"));
    }
}
