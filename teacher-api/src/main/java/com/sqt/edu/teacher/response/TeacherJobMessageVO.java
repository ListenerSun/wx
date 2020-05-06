package com.sqt.edu.teacher.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-05-06 18:28
 */
@ApiModel("贴心老师返回VO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherJobMessageVO implements Serializable {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "学科")
    private String subject;
    @ApiModelProperty(value = "年级")
    private String grade;
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    @ApiModelProperty(value = "单位: 分钟，小时,天，月。和价格配合使用")
    private String unit;
    @ApiModelProperty(value = "所属学校")
    private String graduateSchool;
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH", timezone = "GMT+8")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH", timezone = "GMT+8")
    private Date endTime;
    @ApiModelProperty(value = "teacherId")
    private Long teacherId;
}
