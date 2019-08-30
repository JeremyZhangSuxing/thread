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
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    private final SqlSessionTemplate sqlSessionTemplate;
    @Autowired

    public ThreadService(MsgLogMapper msgLogMapper, Executor executor, RedisTemplate redisTemplate, SqlSessionTemplate sqlSessionTemplate) {
        this.msgLogMapper = msgLogMapper;
        this.executor = executor;
        this.redisTemplate = redisTemplate;
        this.sqlSessionTemplate = sqlSessionTemplate;
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
                log.debug("switch has been turned off--");
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


    public void testInsertBatch() {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        log.debug("start time ---" + new Date());
        for (int i = 0; i < 50000; i++) {
            MsgLogMapper mapper = sqlSession.getMapper(MsgLogMapper.class);
            MsgLog msgLog = MsgLog.builder().clientType("jere").
                    isDeleted(Byte.valueOf("0")).clientType("wx").content("test").fromUserId("1").toUserId("ere").sendFrom(Byte.valueOf("2")).
                    createdBy("5").updatedBy("1").createdTime(new Date()).updatedTime(new Date()).msgType("this type").recordVersion(122).build();
            mapper.insert(msgLog);
        }
        sqlSession.commit();
        sqlSession.close();
        log.debug("end time ---" + new Date());
    }
}
