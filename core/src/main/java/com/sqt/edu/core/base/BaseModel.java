package com.sqt.edu.core.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 基础实体类
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-04 9:44
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 8484911801440906141L;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    protected Long createUser;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date createTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    protected Long updateUser;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date updateTime;

    /**
     * 记录版本
     */
    protected Integer version;

    /**
     * 备注
     */
    protected String comments;
}
