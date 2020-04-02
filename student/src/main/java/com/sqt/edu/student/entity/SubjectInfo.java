package com.sqt.edu.student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sqt.edu.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-12 15:18
 */
@ApiModel
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "subject_info")
public class SubjectInfo extends BaseModel {
    private static final long serialVersionUID = 2883722067708819145L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("科目name")
    private String subjectName;
    @ApiModelProperty("科目code")
    private String subjectCode;

}
