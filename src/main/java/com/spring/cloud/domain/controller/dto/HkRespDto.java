/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.spring.cloud.domain.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author suxing.zhang
 * @date 2019/03/19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HkRespDto {
    /**
     * 返回代码
     */
    private String code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回代码具体
     */
    private String returnJson;
}
