//package com.sqt.edu.course.config;
//
//import com.sqt.edu.core.async.ContextCopyingDecorator;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//
///**
// * @Description:
// * @author: ListenerSun(男, 未婚) 微信:810548252
// * @Date: Created in 2019-12-16 17:06
// */
//@Slf4j
//@Configuration
//public class ConfigBean {
//
//    public static final String ASYNC_EXECUTOR_NAME = "asyncExecutor";
//
//    @Bean(name = ASYNC_EXECUTOR_NAME)
//    public Executor asyncExecutor() {
//        log.info("==========>开始注入线程池 Bean");
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        // for passing in request scope context
//        // 线程上下文拷贝实现类
//        executor.setTaskDecorator(new ContextCopyingDecorator());
//        executor.setCorePoolSize(3);
//        executor.setMaxPoolSize(5);
//        executor.setQueueCapacity(100);
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//        executor.setThreadNamePrefix("AsyncThread-");
//        executor.initialize();
//        return executor;
//    }
//}
