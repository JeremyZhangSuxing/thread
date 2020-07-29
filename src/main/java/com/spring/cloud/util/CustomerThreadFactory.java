package com.spring.cloud.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhang.suxing
 * @date 2020/7/29 22:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerThreadFactory implements ThreadFactory {
    /**
     * 线程名称
     */
    private String threadName;
    /**
     * 线程序列号
     */
    private static final AtomicInteger THREAD_NUMBER = new AtomicInteger(1);


    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, threadName + THREAD_NUMBER.getAndIncrement());
    }


    public static void main(String[] args) {

    }
}
