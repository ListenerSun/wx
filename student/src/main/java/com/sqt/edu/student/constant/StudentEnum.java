package com.sqt.edu.student.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-12 21:03
 */
public final class StudentEnum {

    /**
     * 科目类型
     */
    @AllArgsConstructor
    @Getter
    public enum SubjectType {
        CHINESE("1", "语文"),
        MATH("2", "数学"),
        ENGLISH("3", "英语"),
        PHYSICS("4", "物理"),
        CHEMISTRY("5", "化学"),
       ;
        private String code;
        private String value;
    }
}