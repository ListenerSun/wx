package com.sqt.edu.teacher.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sqt.edu.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-27 15:42
 */
@ApiModel(value = "老师发布家教信息表")
@Data
@Builder
public class TeacherJobMessage extends BaseModel {

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
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "teacherId")
    private Long teacherId;
    @ApiModelProperty(value = "用户id")
    private Long accUserId;
}
