package com.sqt.edu.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sqt.edu.core.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-08 19:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course extends BaseModel {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "课程名字")
    private String courseName;
    @ApiModelProperty(value = "课程描述")
    private String description;
    @ApiModelProperty(value = "课程价格")
    private BigDecimal coursePrice;
    @ApiModelProperty(value = "课程Logo")
    private String courseLogo;
    @ApiModelProperty(value = "是否免费")
    private Boolean isFree;
    @ApiModelProperty(value = "讲师id")
    private Long teacherId;
    @ApiModelProperty(value = "已购买数")
    private Integer countBuy;
    @ApiModelProperty(value = "已学习的人数")
    private Integer countStudy;
}
