package com.spring.cloud.application;

import com.spring.cloud.domain.controller.dto.AuthResponseVo;
import com.spring.cloud.domain.enmus.ApiErrorCode;
import com.spring.cloud.domain.entity.UserInfo;
import com.spring.cloud.domain.exception.BusinessException;
import com.spring.cloud.domain.repository.UserInfoRepository;
import com.spring.cloud.interfaces.dto.ModifyUserDto;
import com.spring.cloud.interfaces.dto.UserInfoDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author zhang.suxing
 * @date 2020/2/1 14:12
 **/
@Service
@Log4j2
public class AuthService {
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public AuthService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * 用户注册方法
     *
     * @param userInfoDto
     * @return
     */
    public AuthResponseVo registerUserInfo(UserInfoDto userInfoDto) {
        if (Objects.nonNull(userInfoRepository.queryByNickName(userInfoDto.getNickName()))) {
            throw BusinessException.buildException(ApiErrorCode.NICK_NAME_ALREADY_REGISTER);
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDto, userInfo);
        //本地数据库版本问题 时间设置应该交由db层面处理
        userInfo.setCreateTime(new Date());
        try {
            userInfoRepository.insertUserInfo(userInfo);
            return AuthResponseVo.success();
        } catch (Exception e) {
            log.error("用户信息保存数据库失败，请重试" + e.getMessage());
        }
        throw BusinessException.buildException(ApiErrorCode.OPERATION_DATE_FAIL);
    }

    /**
     * 用户登录
     *
     * @param nickName
     * @param passWord
     * @return
     */
    public AuthResponseVo signIn(String nickName, String passWord) {
        if (Objects.isNull(userInfoRepository.queryByNickNameAndPassWord(nickName, passWord))) {
            throw BusinessException.buildException(ApiErrorCode.SIGN_FAIL);
        }
        return AuthResponseVo.success();
    }

    /**
     * 修改密码
     *
     * @param userDto
     * @return
     */
    public AuthResponseVo modifyUserInfo(ModifyUserDto userDto) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDto, userInfo);
        try {
            userInfoRepository.updateUserInfo(userInfo);
            return AuthResponseVo.success();
        } catch (Exception e) {
            log.error("更新数据库数据失败");
        }
        throw BusinessException.buildException(ApiErrorCode.OPERATION_DATE_FAIL);
    }
}
