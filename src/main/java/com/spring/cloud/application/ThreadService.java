/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */


package com.spring.cloud.application;

import com.spring.cloud.domain.entity.MsgLog;
import com.spring.cloud.domain.entity.MsgLogExample;
import com.spring.cloud.interfaces.mapper.MsgLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author suxing.zhang
 * @since 2019/4/26
 */
@Service
public class ThreadService {
    private final MsgLogMapper msgLogMapper;

    @Autowired
    public ThreadService(MsgLogMapper msgLogMapper) {
        this.msgLogMapper = msgLogMapper;
    }

    public List<String> getList() {
        MsgLogExample example = new MsgLogExample();
        example.or().andIsDeletedEqualTo(Byte.valueOf("0"));
        // 流的时候做排序
        return msgLogMapper.selectByExample(example).stream().map(MsgLog::getContent).
                sorted((String s1, String s2) -> s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 2)).
                collect(Collectors.toList());
    }
}
