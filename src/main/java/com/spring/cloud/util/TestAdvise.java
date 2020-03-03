package com.spring.cloud.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhang.suxing
 * @date 2020/2/29 15:51
 **/
public class TestAdvise {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        TargetObject targetObject = applicationContext.getBean(TargetObject.class);
        targetObject.method();
    }
}
