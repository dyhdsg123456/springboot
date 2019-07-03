package com.study.springboot;

import com.study.springboot.Bean.User;
import com.study.springboot.util.JedisUtil;
import com.study.springboot.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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
	public void zset() {
		User user = new User();
		user.setName("哈哈哈");
		user.setAge(10);

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

}
