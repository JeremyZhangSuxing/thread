package com.spring.cloud.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author zhang.suxing
 * @date 2020/2/29 15:45
 **/
@Component
@Aspect
public class MyAdvise {

    /**
     * 用注解配置通知順序是固定不变的
     */

    @Before("execution(* *..*.method())")
    public void before() {
        System.out.println("-----before-----");
    }

    @Around(value = "execution(* *..*.method())")
    public int around(ProceedingJoinPoint pdj) throws Throwable {
        System.out.println("-----around start----");
        int i = (int) pdj.proceed();
        System.out.println("-----around end---");
        return i + 1;
    }

    @AfterReturning("execution(* *..*.method())")
    public void afterReturning() {
        System.out.println("------after returning------------");
    }

    @After("execution(* *..*.method())")
    public void after() {
        System.out.println("---after-----");
    }

}
