package com.spring.cloud.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhang.suxing
 * @date 2020/3/19 13:59
 **/
public class CasOriginDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        Thread mainThread = new Thread(() -> {
            try {
                int expect = atomicInteger.get();
                Thread.sleep(1000L);
//                boolean result = atomicInteger.compareAndSet(expect)
                System.out.println(Thread.currentThread().getName() + "当前值 = " + atomicInteger.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
