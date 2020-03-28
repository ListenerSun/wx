package com.sqt.edu.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Description: 测试redis排行榜实体类
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-26 10:01
 */
@Data
@TableName("user_score")
public class UserScore implements Serializable {

    private int userId;
    private int score;
    private Date createTime;

}
