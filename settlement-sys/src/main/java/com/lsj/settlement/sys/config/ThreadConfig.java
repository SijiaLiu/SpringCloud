package com.lsj.settlement.sys.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * class_name: threadConfig
 * package: com.lsj.settlement.sys.config
 * describe: TODO
 * @author liusijia
 * @Date 2018/11/2
**/


@Configuration
@EnableAsync
@Slf4j
public class ThreadConfig {

    @Bean
    public Executor asyncInput() {
        log.info("asyncInputConfig ");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1 );
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(1);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("sijia-%d").build();
        executor.setThreadFactory(namedThreadFactory);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
