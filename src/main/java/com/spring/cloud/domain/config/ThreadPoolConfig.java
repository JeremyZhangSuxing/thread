/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.spring.cloud.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author suxing.zhang
 * @date 2019/03/21
 */
@EnableAsync
@Configuration
@Component
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
                new LinkedBlockingQueue<Runnable>(100),
                new CustomizableThreadFactory("common-logger-thread-"),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

}