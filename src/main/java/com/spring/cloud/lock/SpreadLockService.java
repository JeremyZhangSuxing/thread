package com.spring.cloud.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

/**
 * @author zhang.suxing
 * @date 2020/3/31 15:42
 **/
@Service
public class SpreadLockService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private long timeOut = 10000;

    private long sleepTime = 1000;

    private int maxRetryTimes = 3;

    private String localKey = "TEST_SPREAD_";

    public void spreadLock(String orderNo, String businessNo) {
        String reqId = UUID.randomUUID().toString();
        String key = localKey + orderNo + businessNo;
        Boolean lock = tryLock(key, reqId);
        int retry = 0;
        while (!lock && ++retry < maxRetryTimes) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock = tryLock(key, reqId);
        }
        if (!lock) {
            System.out.println("get lock failed");
        } else {
            System.out.println("get lock success");
            //执行业务
        }
    }

    private Boolean tryLock(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofMillis(timeOut));
    }

}
