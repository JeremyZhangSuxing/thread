package com.spring.cloud.domain.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhang.suxing
 * @date 2020/2/1 14:51
 **/
@Getter
@AllArgsConstructor
public enum ApiErrorCode {
    SUCCESS("0", "请求成功"),
    FAIL("1", "请求失败"),
    NICK_NAME_ALREADY_REGISTER("1001001", "用户昵称已经被注册"),
    SIGN_FAIL("1001002", "用户名密码不匹配，请重试"),
    OPERATION_DATE_FAIL("10011003", "操作失败，请重试"),;
    /**
     * 异常代码
     */
    private String code;
    /**
     * 异常信息
     */
    private String message;
}
