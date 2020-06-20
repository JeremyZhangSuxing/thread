package com.spring.cloud.domain.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhang.suxing
 * @date 2020/6/16 22:38
 **/
@AllArgsConstructor
@Getter
public enum SeasonEnum {

    /**
     * spring
     */
    SPRING("春天", "春暖花开"),;

    private String seasonName;

    private String seasonDesc;

}
