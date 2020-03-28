package com.spring.cloud.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhang.suxing
 * @date 2020/3/28 15:34
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void testRedisTemplate() {
//        stringRedisTemplate.opsForValue().append("jere","suxing");
//        System.out.println(stringRedisTemplate.opsForValue().get("jere"));
        stringRedisTemplate.opsForList().leftPush("1", "1", "2");

    }

    @Test
    public void testRedisTemplateList() {

    }

}
