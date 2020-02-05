package com.spring.cloud.domain.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.cloud.domain.enmus.ApiErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang.suxing
 * @date 2020/2/1 11:38
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponseVo<T> {
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应code
     */
    private String code;
    /**
     * 返回对象，非必输
     */
    private T data;

    public AuthResponseVo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static AuthResponseVo success() {
        return new AuthResponseVo(ApiErrorCode.SUCCESS.getCode(), ApiErrorCode.SUCCESS.getMessage());
    }

    public static AuthResponseVo result(String code, String msg) {
        return new AuthResponseVo(code, msg);
    }

    public static <T> AuthResponseVo buildAutoRes(String code, String msg, T data) {
        AuthResponseVo<T> AuthResponseVo = new AuthResponseVo<>();
        AuthResponseVo.setCode(code);
        AuthResponseVo.setMsg(msg);
        AuthResponseVo.setData(data);
        return AuthResponseVo;
    }

}
