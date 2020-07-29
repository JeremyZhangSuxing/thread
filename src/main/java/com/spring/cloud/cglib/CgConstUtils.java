package com.spring.cloud.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @author zhang.suxing
 * @date 2020/7/23 22:45
 **/
public class CgConstUtils {
    /**
     * 通用的代理方法工具类
     *
     * @param classes
     * @param method
     * @return
     */
    public static Object getProxyInstance(Class[] classes, MethodInterceptor method) {
        Enhancer en = new Enhancer();
        en.setSuperclass(classes[0]);
        en.setCallback(method);
        return en.create();
    }
}
