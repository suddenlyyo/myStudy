package com.zx.jedis;

import com.zx.MyStudyApplicationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program: myStudy
 * @description: jedis池测试
 * @author: zhou  xun
 * @create: 2023-07-04 18:19
 */
public class JedisPoolTest  extends MyStudyApplicationTest {
    @Autowired
    private JedisPool jedisPool;
    @DisplayName("string Test")
    @Test
    void stringTest() {
        Jedis jedis = jedisPool.getResource();
        //调用对应的方法操作
        jedis.set("username", "zhangsan");
        String username = jedis.get("username");
        //可存储指定过期时间的数据
        jedis.setex("activeCode", 20, "valueString");
        System.out.println(username);
    }

}
