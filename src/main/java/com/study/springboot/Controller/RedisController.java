package com.study.springboot.Controller;

import com.study.springboot.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: dyh
 * Date:   2019/6/21
 * Description:
 */
@RestController
public class RedisController {
@Autowired
    RedisUtil redisUtil;
    @RequestMapping("/")
    String home() {
        boolean set = redisUtil.set("test1", "asdasdasd");
        Object test1 = redisUtil.get("test1");
        System.out.println(test1);
        return "Hello World!";
    }
}
