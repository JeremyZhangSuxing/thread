package com.spring.cloud.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhang.suxing
 * @date 2020/3/3 16:04
 **/
public class CallMethod implements MethodInterceptor {

    /**
     * @param proxyObject 代理对象
     * @param method      目标对象的方法
     * @param objects     目标钟对象的方法的参数
     * @param methodProxy 代理对象中的代理方法对象
     * @return 代理方法执行结果
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Object proxyObject, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("---前置方法---");
        Object o1 = methodProxy.invokeSuper(proxyObject, objects);
        System.out.println("---后置方法---");
        return o1;
    }
}
