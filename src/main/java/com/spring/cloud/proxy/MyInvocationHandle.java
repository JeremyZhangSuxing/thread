package com.spring.cloud.proxy;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhang.suxing
 * @date 2020/3/3 11:17
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MyInvocationHandle implements InvocationHandler {
    /**
     * 目标对象传递进来
     */
    private Object targetObject;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equalsIgnoreCase("add")) {
            return method.invoke(targetObject);
        }
        System.out.println("----代理目标对象目标方法之前的执行---");
        Object returnResult = method.invoke(targetObject, args);
        System.out.println("----代理目标对象目标方法之后的执行---");
        return returnResult;
    }
}
