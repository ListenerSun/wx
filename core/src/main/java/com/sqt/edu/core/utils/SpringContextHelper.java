package com.sqt.edu.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description: 容器工具类
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-27 19:30
 */
@Slf4j
@Component
public class SpringContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == SpringContextHelper.applicationContext) {
            SpringContextHelper.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<?> requiredType) throws BeansException {
        return applicationContext.getBean(name, (Class<T>) requiredType);
    }

    public static <T> T getBean(Class<?> requiredType) throws BeansException {
        String[] names = applicationContext.getBeanNamesForType(requiredType);
        if (names == null || names.length == 0) {
            throw new IllegalArgumentException("没有找到Bean，类型：" + requiredType.getName());
        }
        return (T) applicationContext.getBean(names[0]);
    }

    public static <T> Collection<T> getBeans(String... names) throws BeansException {
        Collection<T> beans = new ArrayList<>();
        for (String name : names) {
            beans.add((T) applicationContext.getBean(name));
        }
        return beans;
    }

    public static <T> Map<Object, T> getBeans(Class<?> requiredType) throws BeansException {
        Map<Object, T> beans = new HashMap<>();
        String[] names = applicationContext.getBeanNamesForType(requiredType);
        for (String name : names) {
            beans.put(name, (T) applicationContext.getBean(name));
        }
        return beans;
    }

    public static <T> Map<Object, T> getBeans(Class<?> requiredType, Class<?>... excludeTypes) throws BeansException {
        Map<Object, T> beans = new HashMap<>();
        String[] names = applicationContext.getBeanNamesForType(requiredType);
        for (String name : names) {
            Class type = applicationContext.getType(name);
            if (excludeTypes != null) {
                boolean flag = Arrays.stream(excludeTypes)
                        .filter(excludeType -> excludeType.equals(type))
                        .findAny()
                        .isPresent();

                if (flag) {
                    continue;
                }
            }
            beans.put(name, (T) applicationContext.getBean(name));
        }
        return beans;
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }
}