package com.spring.cloud.domain.repository;

import com.spring.cloud.domain.entity.UserInfo;

/**
 * @author zhang.suxing
 * @date 2020/2/1 14:25
 **/
public interface UserInfoRepository {

    /**
     * 根据昵称获取用户信息
     *
     * @param nickName 用户昵称
     * @return
     */
    UserInfo queryByNickName(String nickName);

    /**
     * 用户新增
     *
     * @param userInfo 用户基础信息
     * @return
     */
    int insertUserInfo(UserInfo userInfo);

    /**
     * 验证用户名和密码是否正确
     *
     * @param nickName
     * @param passWord
     * @return
     */
    UserInfo queryByNickNameAndPassWord(String nickName, String passWord);

    /**
     * 修改用户密码
     *
     * @param userInfo
     * @return
     */
    int updateUserInfo(UserInfo userInfo);

}
