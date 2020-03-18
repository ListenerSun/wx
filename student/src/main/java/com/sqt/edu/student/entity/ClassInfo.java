package com.sqt.edu.student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sqt.edu.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 补课班级信息
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-12 15:03
 */
@ApiModel
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "class_info")
public class ClassInfo extends BaseModel {

    private static final long serialVersionUID = 2603141719763516683L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("班级名称")
    private String className;
    @ApiModelProperty("补课科目")
    private String subjects;
    @ApiModelProperty("年级")
    private String grade;
    @ApiModelProperty("补课天数")
    private Integer days;
    @ApiModelProperty("年份")
    private Integer year;
    @ApiModelProperty("已报名学生数量")
    private Integer hasAmount;
    @ApiModelProperty("计划招收学生数量")
    private Integer planAmount;
    @ApiModelProperty("假期类型: 1:暑假,2:寒假")
    private String vacationType;
}
