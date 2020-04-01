package com.sqt.edu.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-16 9:32
 */
public final class CommonEnum {

    /**
     * 数据源类型
     */
    @AllArgsConstructor
    @Getter
    public enum DsType{
        DS_MASTER("master"),
        DS_SLAVE("slave"),
        ;
        private String value;


    }

    /**
     * 课程类型
     */
    @AllArgsConstructor
    @Getter
    public enum CourseType{
        ;
        private String code;
        private String value;

    }

}
