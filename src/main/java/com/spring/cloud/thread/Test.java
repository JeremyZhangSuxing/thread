/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.spring.cloud.thread;


import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * @author suxing.zhang
 * @since 2019/4/29
 */
public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        date = DateUtils.addMinutes(new Date(), -3);
        System.out.println(date);
    }
}
