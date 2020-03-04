package com.spring.cloud.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author zhang.suxing
 * @date 2020/3/3 16:04
 **/
public class TestCglib {
    public static void main(String[] args) {
        /**
         * cglib 动态代理  目标对象的子类作为代理对象
         * MethodInterceptor 实现callback子接口对目标对象的方法进行改装
         *
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Student.class);
        Callback callback = new CallMethod();
        enhancer.setCallback(callback);
        Student student = (Student) enhancer.create();
        student.study();
    }
}
