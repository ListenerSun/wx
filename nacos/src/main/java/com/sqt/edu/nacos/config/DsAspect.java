package com.sqt.edu.nacos.config;

import com.sqt.edu.common.annotation.DS;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description: 数据源切换切面
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-17 10:24
 */
@Order(0)
@Aspect
@Component
@Slf4j
public class DsAspect {
    /**
     * 配置AOP切面的切入点
     * 切换放在service接口的方法上
     */
    @Pointcut("execution(* com.sqt..service..*Service.*(..))")
    public void dataSourcePointCut() {
    }

    /**
     * 根据切点信息获取调用函数是否用TargetDataSource切面注解描述，
     * 如果设置了数据源，则进行数据源切换
     */
    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            if (null != m && m.isAnnotationPresent(DS.class)) {
                DS td = m.getAnnotation(DS.class);
                String dbStr = td.value();
                log.info("==========>current thread {} add dataSource[{}] to ThreadLocal, request method name is : {}",
                        Thread.currentThread().getName(), dbStr, method);
            }
        } catch (Exception e) {
            log.error("==========>current thread {} add data to ThreadLocal error,{}", Thread.currentThread().getName(), e);
            throw e;
        }
    }


    /**
     * 执行完切面后，将线程共享中的数据源名称清空，
     * 数据源恢复为原来的默认数据源
     */
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint) {
        log.info("==========>clean datasource[{}]");
    }

}
