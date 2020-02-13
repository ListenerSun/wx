package com.sqt.edu.course.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-21 15:05
 */
@Data
@ToString
public class JwtSubject implements Serializable {

    private Long accUserId;
    private String type;

    public JwtSubject(Long accUserId, String type) {
        this.accUserId = accUserId;
        this.type = type;
    }
}
