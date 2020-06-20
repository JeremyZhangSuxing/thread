package com.spring.cloud.enums;

import com.spring.cloud.domain.enmus.SeasonEnum;

/**
 * @author zhang.suxing
 * @date 2020/6/16 22:06
 * <p>
 * <p>
 * 一、枚举类的使用
 * 1.枚举类的理解，类的对象只有有限个，确定的。我们称此类为枚举类
 * 2.当定义一组常量时，强烈建议使用枚举类
 * 3.如果使用枚举类中只有一个对象，则可以作为单例模式来使用
 * <p>
 * <p>
 * 二、如何定义枚举类
 * 1.自定义枚举类
 * 2.enum枚举类 继承Enum
 * <p>
 * <p>
 * 三、enum中常用的方法
 * 1.valueOf 如果不存在这个值则会报错 转化为map 来操作
 * <p>
 * 四、
 * 1.使用enum实现interface
 * 2.不同的美剧不同的实现
 * <p>
 * 五、使用枚举类做策略模式
 * 一个接口有多个service实现时 根据beanName使用注入beamMap 符合策略模式使用
 **/
public class EnumsTest {

    public static void main(String[] args) {
        SeasonEnum seasonEnum = SeasonEnum.SPRING;

        System.out.println(seasonEnum);

        System.out.println(seasonEnum.getClass().getSuperclass());
    }


}
