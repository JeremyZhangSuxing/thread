package com.spring.cloud.reflect;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang.suxing
 * @date 2020/6/20 13:20
 **/
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Person {

    private String name;

    int age;

    public void show() {
        System.out.println("this person whose name is jeremy");
    }


}
