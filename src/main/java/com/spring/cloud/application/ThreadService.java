/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */


package com.spring.cloud.application;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.cloud.domain.entity.MsgLog;
import com.spring.cloud.domain.entity.MsgLogExample;
import com.spring.cloud.interfaces.mapper.MsgLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * @author suxing.zhang
 * @since 2019/4/26
 */
@Service
@Slf4j
public class ThreadService {
    private final MsgLogMapper msgLogMapper;
    private final Executor executor;
    private final RedisTemplate redisTemplate;

    private static final String SYNC_HK_SWITCH = "accounting.sync.switch";

    @Autowired
    public ThreadService(MsgLogMapper msgLogMapper, Executor executor, RedisTemplate redisTemplate) {
        this.msgLogMapper = msgLogMapper;
        this.executor = executor;
        this.redisTemplate = redisTemplate;
    }

    public List<String> getList() {
        MsgLogExample example = new MsgLogExample();
        example.or().andIsDeletedEqualTo(Byte.valueOf("0"));
        // 流的时候做排序
        return msgLogMapper.selectByExample(example).stream().map(MsgLog::getContent).
                sorted((String s1, String s2) -> s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 2)).
                collect(Collectors.toList());
    }

    public PageInfo<String> getLogsPage(int start, int pageSize) {
        PageHelper.startPage(start, pageSize);
        MsgLogExample example = new MsgLogExample();
        example.or().andIsDeletedEqualTo(Byte.valueOf("0"));
        List<String> list = msgLogMapper.selectByExample(example).stream().map(MsgLog::getContent).collect(Collectors.toList());
        PageInfo<String> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public String testThread() {
        while (true) {
            boolean switchFlag = redisTemplate.opsForValue().get(SYNC_HK_SWITCH) == null ? true : false;
            if (switchFlag) {
                log.debug("switch has been turned off");
                break;
            }
            log.debug("switch has been turned on");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                log.debug(e.getMessage());
            }
        }
        return "this is a switch";
    }
}
