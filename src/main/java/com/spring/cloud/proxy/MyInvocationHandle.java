package com.spring.cloud.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhang.suxing
 * @date 2020/3/3 11:17
 **/
public class MyInvocationHandle implements InvocationHandler {

    private Object targetObject;

    public MyInvocationHandle(Object targetObject) {
        this.targetObject = targetObject;
    }

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
