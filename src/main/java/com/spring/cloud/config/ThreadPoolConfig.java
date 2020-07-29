/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.spring.cloud.config;

import com.spring.cloud.util.CustomerThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author suxing.zhang
 * @date 2019/03/21
 */
@EnableAsync
@Configuration
public class ThreadPoolConfig {
    @Bean("executor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(1024);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("thread-for-reqHk-pool-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(1000);
        return executor;
    }

    /**
     * 通用的报警线程池
     */
    @Bean("commonAlarmThread")
    public ExecutorService commonAlarmThread() {
        return new ThreadPoolExecutor(1,
                3,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                new CustomizableThreadFactory("common-logger-thread-"),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    /**
     * 通用业务线程池
     */
    @Bean("commonUserThread")
    public ExecutorService commonUserThread() {
        return new ThreadPoolExecutor(
                10, 50, 3, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(5000),
                new CustomizableThreadFactory("user_common_thread-"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 构建同步同步队列线程池
     */
    public static ThreadPoolExecutor customerThreadPool() {
        return new ThreadPoolExecutor(0,
                Integer.MAX_VALUE,
                1,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new CustomerThreadFactory("customerThread"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}