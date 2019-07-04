package com.study.springboot;

import com.study.springboot.Bean.User;
import com.study.springboot.util.JedisUtil;
import com.study.springboot.util.RedisUtil;
import com.study.springboot.util.SimpleRateLimiter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
	@Autowired
	private RedisUtil                     redisUtil;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
   @Autowired
	private JedisUtil                     jedisUtil;
	@Autowired
	private JedisPool                     jedisPool;

	/**
	 * set get
	 */
	@Test
	public void contextLoads() {
		User user = new User();
		user.setName("哈哈哈");
		user.setAge(10);
		redisUtil.set("user",user);
		User user1 = (User) redisUtil.get("user");
		System.out.println(user1);
//		redisUtil.del("user");
	}


	@Test
	public void test1() {
		Jedis jedis = jedisPool.getResource();
//		Set<String> keys = jedis.keys("*");
//		for (String key : keys) {
//			System.out.println(key);
//		}
//		for (int i=0;i<10000;i++){
//			jedis.set("key"+i,i+"");
//		}
		jedis.close();
	}

	@Test
	public void pfadd() {
		Jedis jedis = jedisPool.getResource();
//		for (int i=0;i<1000;i++){
//			jedis.pfadd("codehole2","user"+i);
//		}
//		long total = jedis.pfcount("codehole2");
//
//		System.out.printf("%d %d\n", 100000, total);
//		String pfmerge = jedis.pfmerge("codehole3", "codehole2", "codehole");
		System.out.println(jedis.pfcount("codehole3"));
		jedis.close();
	}

	@Test
	public void simpleRateLimiter() throws IOException, InterruptedException {
		Jedis jedis = new Jedis();
		SimpleRateLimiter limiter = new SimpleRateLimiter(jedis);
		for(int i=0;i<20;i++) {
			//1分钟内最多5次
			System.out.println(limiter.isActionAllowed("dyh", "look", 60, 5));
			Thread.sleep(1000);
		}

	}
}
