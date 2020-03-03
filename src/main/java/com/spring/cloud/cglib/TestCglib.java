package com.spring.cloud.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author zhang.suxing
 * @date 2020/3/3 16:04
 **/
public class TestCglib {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Student.class);
        Callback callback = new CallMethod();
        enhancer.setCallback(callback);
        Student student = (Student) enhancer.create();
        student.study();
    }
}
