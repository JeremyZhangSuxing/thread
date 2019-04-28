package com.spring.cloud.interfaces.mapper;

import com.spring.cloud.domain.entity.MsgLog;
import com.spring.cloud.domain.entity.MsgLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MsgLogMapper {
    long countByExample(MsgLogExample example);

    int deleteByExample(MsgLogExample example);

    int insert(MsgLog record);

    int insertSelective(MsgLog record);

    List<MsgLog> selectByExample(MsgLogExample example);

    int updateByExampleSelective(@Param("record") MsgLog record, @Param("example") MsgLogExample example);

    int updateByExample(@Param("record") MsgLog record, @Param("example") MsgLogExample example);
}