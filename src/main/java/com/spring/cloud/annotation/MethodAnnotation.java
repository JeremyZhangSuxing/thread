package com.spring.cloud.annotation;

import java.lang.annotation.*;

/**
 * @author zhang.suxing
 * @date 2020/6/20 9:54
 * <p>
 * <p>
 * 1.注解是一种元数据形式。即注解是属于java的一种数据类型，和类、接口、数组、枚举类似。
 * 2.注解用来修饰，类、方法、变量、参数、包。
 * 3.注解不会对所修饰的代码产生直接的影响。
 * java 元注解的使用
 * @Target 不指定可用于所有元素上
 * @Retention 指定注解的生命周期  米热播runtime
 * @Documented java doc 注释
 * @Inherited 修饰的注解具有继承性
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodAnnotation {
    int age() default 25;

    String name() default "jeremy";

    int[] array();
}
