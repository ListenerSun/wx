package com.sqt.edu.student.dto.resp;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * @Date: Created in 2020-03-19 19:58
 */
@ApiModel("学生报名信息返回Vo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuRegisterInfoVo implements Serializable {

    @Excel(name = "序号")
    private Long id;
    @Excel(name = "学生姓名")
    @ApiModelProperty("学生姓名")
    private String studentName;
    @Excel(name = "联系方式",width = 15)
    @ApiModelProperty("学生联系方式")
    private String phone;
    @Excel(name = "地址")
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("补课班级信息Id")
    private Long classInfoId;
    @Excel(name = "班级名称",width = 15)
    @ApiModelProperty("补课班级名称")
    private String className;
    @Excel(name = "补课年级")
    @ApiModelProperty("补课年级")
    private String grade;
    @Excel(name = "补课科目")
    @ApiModelProperty("补课科目")
    private String subjects;
    @Excel(name = "年份")
    @ApiModelProperty("年份")
    private Integer year;
}
