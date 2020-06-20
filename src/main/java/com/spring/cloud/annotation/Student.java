package com.spring.cloud.annotation;

/**
 * @author zhang.suxing
 * @date 2020/6/20 10:01
 **/
public class Student {

    @MethodAnnotation(name = "Jonson", age = 19, array = {11, 12})
    private void study(int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("jeremy is studying!");
        }
    }
}
