package com.spring.cloud.application;

import com.spring.cloud.domain.entity.UserInfo;
import com.spring.cloud.interfaces.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhang.suxing
 * @date 2020/3/28 20:53
 **/
@Service
public class CacheService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    //    @Cacheable(cacheNames = "msgLog")
    public UserInfo cacheUser(Long id) {
        System.out.println("查询数据库 " + id + " 号员工");
        UserInfo userInfo = userInfoMapper.getById(id);
        return userInfo;
    }
}
