package com.spring.cloud.thread;

import com.spring.cloud.domain.entity.UserInfo;
import com.spring.cloud.interfaces.mapper.UserInfoMapper;
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
    private RedisTemplate<Object, UserInfo> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 常见 redis 操作的五大类型
     * String List(列表) Set Hash(散列) ZSet(有序集合)
     */
    @Test
    public void testRedisTemplate() {
//        stringRedisTemplate.opsForValue().append("jere","suxing");
//        System.out.println(stringRedisTemplate.opsForValue().get("jere"));
        stringRedisTemplate.opsForList().leftPush("1", "1", "2");

    }

    @Test
    public void testRedisTemplateList() {
        UserInfo userInfo = userInfoMapper.getById(77L);
        redisTemplate.opsForValue().set("user-01", userInfo);
    }

}
