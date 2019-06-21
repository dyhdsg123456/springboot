package com.study.springboot;

import com.study.springboot.Bean.User;
import com.study.springboot.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
	@Autowired
	RedisUtil redisUtil;

	@Test
	public void contextLoads() {
		User user = new User();
		user.setName("哈哈哈");
		user.setAge(10);
		redisUtil.set("user",user);
		User user1 = (User) redisUtil.get("user");
		System.out.println(user1);
	}

}
