package com.spring.cloud.util;

import org.springframework.stereotype.Component;

/**
 * @author zhang.suxing
 * @date 2020/2/29 15:49
 **/
@Component
public class TargetObject {

    public int method() {
        System.out.println("------测试目标对象方法是否执行----");
        return 10;
    }
}
