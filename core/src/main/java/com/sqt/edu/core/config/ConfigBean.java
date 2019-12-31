//package com.sqt.edu.core.config;
//
//import com.sqt.edu.core.async.ContextCopyingDecorator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.ThreadPoolExecutor;
//
///**
// * @Description:
// * @author: ListenerSun(男, 未婚) 微信:810548252
// * @Date: Created in 2019-12-16 17:06
// */
//@Configuration
//public class ConfigBean {
//
//    public static final String ASYNC_EXECUTOR_NAME = "asyncExecutor";
//
//    @Bean(name = ASYNC_EXECUTOR_NAME)
//    public Executor asyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(10);
//        executor.setMaxPoolSize(20);
//        executor.setQueueCapacity(200);
//        executor.setKeepAliveSeconds(60);
//        executor.setThreadNamePrefix("taskExecutor-");
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//        executor.setAwaitTerminationSeconds(60);
//        // 增加 TaskDecorator 属性的配置
//        executor.setTaskDecorator(new ContextCopyingDecorator());
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.initialize();
//        return executor;
//    }
//}
