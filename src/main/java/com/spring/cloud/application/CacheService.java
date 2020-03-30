package com.spring.cloud.application;

import com.spring.cloud.domain.entity.UserInfo;
import com.spring.cloud.interfaces.mapper.UserInfoMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhang.suxing
 * @date 2020/3/28 20:53
 **/
@Service
@CacheConfig(cacheNames = "user")
public class CacheService {
    /**
     * CacheAutoConfiguration
     * 1.缓存的配置类有很多
     */

    @Resource
    private UserInfoMapper userInfoMapper;

    @Cacheable(key = "#id", condition = "#id >90")
    public UserInfo cacheUser(Long id) {
        System.out.println("查询数据库 " + id + " 号员工");
        return userInfoMapper.getById(id);

    }
}
