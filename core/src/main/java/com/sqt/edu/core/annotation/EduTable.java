package com.sqt.edu.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Description: 自定义 注解
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-04 10:47
 */
@Target(ElementType.TYPE)
public @interface EduTable {

    String value() default "";

    String schema() default "";
}
