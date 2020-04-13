package com.sqt.edu.common.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-18 9:32
 */
@ApiModel
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dic_data")
public class SysDicData implements Serializable {

    /**
     * code
     */
    private String code;
    /**
     * name
     */
    private String name;
    /**
     * typeCode
     */
    private String dicTypeCode;
}
