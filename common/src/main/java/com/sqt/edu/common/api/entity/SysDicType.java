package com.sqt.edu.common.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-18 9:32
 */
@Data
@Builder
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dic_type")
public class SysDicType implements Serializable {
    private static final long serialVersionUID = -3519038523243798719L;

    /**
     * code
     */
    @TableId
    private String code;
    /**
     * name
     */
    private String name;
}
