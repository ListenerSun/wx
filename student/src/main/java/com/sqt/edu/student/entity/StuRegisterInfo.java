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
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-03-16 10:26
 */
@ApiModel
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "stu_register_info")
public class StuRegisterInfo extends BaseModel {

    private static final long serialVersionUID = 6066845796839169527L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("学生姓名")
    private String studentName;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("就读院校")
    private String school;
    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("补课班级信息Id")
    private Long classInfoId;
    @ApiModelProperty("补课科目")
    private String subjects;
    @ApiModelProperty("用户id")
    private Long accUserId;
}