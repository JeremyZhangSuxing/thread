package com.spring.cloud.domain.exception;

import com.spring.cloud.domain.enmus.ApiErrorCode;

/**
 * @author zhang.suxing
 * @date 2020/2/1 14:44
 **/
public class BusinessException extends RuntimeException {
    private String errCode;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(ApiErrorCode exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.errCode = exceptionEnum.getCode();
    }

    /**
     * 构建异常信息方法
     *
     * @param exceptionEnum
     * @return
     */
    public static BusinessException buildException(ApiErrorCode exceptionEnum) {
        return new BusinessException(exceptionEnum);
    }

}
