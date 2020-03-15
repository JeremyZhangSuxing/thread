package com.spring.cloud.application;

import com.spring.cloud.domain.annotion.Idempotent;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author zhang.suxing
 * @date 2020/2/1 14:12
 **/
@Service
@Log4j2
public class AuthService {
    private final UserInfoRepository userInfoRepository;

    @Autowired
    @Qualifier("commonUserThread")
    private ExecutorService executorService;

    @Autowired
    public AuthService(UserInfoRepository userInfoRepository
    ) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * 用户注册方法
     */
    @Idempotent(key = "#req.nickName + '_' + #req.phone")
    public AuthResponseVo registerUserInfo(UserInfoDto req) {
        if (Objects.nonNull(userInfoRepository.queryByNickName(req.getNickName()))) {
            throw BusinessException.buildException(ApiErrorCode.NICK_NAME_ALREADY_REGISTER);
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(req, req);
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
     */
    public AuthResponseVo signIn(String nickName, String passWord) {
        if (Objects.isNull(userInfoRepository.queryByNickNameAndPassWord(nickName, passWord))) {
            throw BusinessException.buildException(ApiErrorCode.SIGN_FAIL);
        }
        return AuthResponseVo.success();
    }

    /**
     * 修改密码
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

    /**
     * 测试并发编程
     */
    public AuthResponseVo runThreadLocal() {
        List<UserInfo> list = userInfoRepository.queryAll();
        List<Callable<Boolean>> futureTasks = new ArrayList<>(list.size());
        list.forEach(userInfo -> futureTasks.add(() -> asnyToPrintLog(userInfo)));
        List<Future<Boolean>> result = new ArrayList<>(futureTasks.size());
        try {
            result = executorService.invokeAll(futureTasks, 5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        int success = 0;
        for (Future<Boolean> future : result) {
            try {
                Boolean occupyResult = future.get(1, TimeUnit.SECONDS);
                if (occupyResult) {
                    success++;
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (success != list.size()) {
            return AuthResponseVo.result("1", "并发调用失败");
        }
        return AuthResponseVo.success();
    }

    private Boolean asnyToPrintLog(UserInfo userInfo) {
        log.info(String.format("并发打印日志：用户名【%s】,手机号码【%s】", userInfo.getNickName(), userInfo.getPhone()));
        return Boolean.TRUE;
    }

}
