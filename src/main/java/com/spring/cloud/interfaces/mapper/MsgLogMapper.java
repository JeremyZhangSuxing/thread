package com.spring.cloud.interfaces.mapper;

import com.spring.cloud.domain.entity.MsgLog;
import com.spring.cloud.domain.entity.MsgLogExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MsgLogMapper {
    long countByExample(MsgLogExample example);

    int deleteByExample(MsgLogExample example);

    int insert(MsgLog record);

    int insertSelective(MsgLog record);

    List<MsgLog> selectByExample(MsgLogExample example);

    int updateByExampleSelective(@Param("record") MsgLog record, @Param("example") MsgLogExample example);

    int updateByExample(@Param("record") MsgLog record, @Param("example") MsgLogExample example);

    @Select("select * from msg_log where id = # {id}")
    MsgLog selectById(Long id);
}