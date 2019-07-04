package com.study.springboot.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * Author: dyh
 * Date:   2019/7/3
 * Description:redis 窗口限流
 */
public class SimpleRateLimiter {
    private Jedis jedis;
    public SimpleRateLimiter(Jedis jedis){
        this.jedis=jedis;
    }
    public Boolean isActionAllowed(String userId,String actionKey,int period,int maxCount) throws IOException {
        String key = MessageFormat.format("hist{0}:{1}", userId, actionKey);
        long nowtime = System.currentTimeMillis();
        Pipeline pipe = jedis.pipelined();//使用管道
        pipe.multi();//开始事务
        pipe.zadd(key,nowtime,""+nowtime);//记录行为
        //# 移除时间窗口之前的行为记录，剩下的都是时间窗口内的
        pipe.zremrangeByScore(key,0,nowtime-1000*period);
        //# 获取窗口内的行为数量
        Response<Long> count = pipe.zcard(key);
        //# 设置 zset 过期时间，避免冷用户持续占用内存
        //# 过期时间应该等于时间窗口的长度，再多宽限 1s
        pipe.expire(key,period+1);
        pipe.exec();
        pipe.close();
        return count.get()<=maxCount;
    }
}
