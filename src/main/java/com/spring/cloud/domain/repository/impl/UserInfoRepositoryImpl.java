package com.spring.cloud.domain.repository.impl;

import com.spring.cloud.domain.entity.UserInfo;
import com.spring.cloud.domain.entity.UserInfoExample;
import com.spring.cloud.domain.repository.UserInfoRepository;
import com.spring.cloud.interfaces.mapper.UserInfoMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhang.suxing
 * @date 2020/2/1 14:27
 **/
@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 根据昵称获取用户信息
     *
     * @param nickName 用户昵称
     * @return
     */
    @Override
    public UserInfo queryByNickName(String nickName) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andNickNameEqualTo(nickName);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 用户新增
     *
     * @param userInfo 用户基础信息
     * @return
     */
    @Override
    public int insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insertSelective(userInfo);
    }

    /**
     * 验证用户名和密码是否正确
     *
     * @param nickName 用户昵称
     * @param passWord 密码
     * @return
     */
    @Override
    public UserInfo queryByNickNameAndPassWord(String nickName, String passWord) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andNickNameEqualTo(nickName)
                .andPassWordEqualTo(passWord);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 修改用户密码
     *
     * @param userInfo
     * @return
     */
    @Override
    public int updateUserInfo(UserInfo userInfo) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andNickNameEqualTo(userInfo.getNickName());
        return userInfoMapper.updateByExampleSelective(userInfo, example);
    }

    /**
     * 获取全部用户
     *
     * @return
     */
    @Override
    public List<UserInfo> queryAll() {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andDeletedEqualTo((byte) 0);
        return userInfoMapper.selectByExample(example);
    }
}
