package com.sqt.edu.common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-16 19:20
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( { ElementType.METHOD , ElementType.TYPE } )
public @interface DS {
    @AliasFor( "dataSource" )
    String value () default "master";

    @AliasFor( "value" )
    String dataSource () default "master";

}
