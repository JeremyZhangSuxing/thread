/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.spring.cloud.domain.controller.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author suxing.zhang
 * @date 2019/03/24
 */
@Data
@Builder
public class CommonResDto {
    /**
     * 返回代码
     */
    private Integer resultCode;
    /**
     * 返回具体信息
     */
    private String resultMsg;
}
