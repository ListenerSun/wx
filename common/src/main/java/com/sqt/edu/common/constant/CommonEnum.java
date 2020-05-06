package com.sqt.edu.common.constant;

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
        COURSE_TYPE_1("1","后台"),
        COURSE_TYPE_2("2","前端"),
        COURSE_TYPE_3("3","大数据"),
        COURSE_TYPE_4("4","小程序"),
        COURSE_TYPE_5("5","基础理论"),
        ;
        private String code;
        private String value;

    }

}
