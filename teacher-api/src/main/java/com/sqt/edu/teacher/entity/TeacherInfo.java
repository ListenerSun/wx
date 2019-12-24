package com.sqt.edu.teacher.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sqt.edu.core.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 老师信息
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-24 15:59
 */
@ApiModel
@Builder
@Data
@TableName(value = "teacher_info")
public class TeacherInfo extends BaseModel {

    private static final long serialVersionUID = 8627804769015263595L;

    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("姓名")
    private String teacherName;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("生日")
    private Date birthday;
    @ApiModelProperty("毕业院校")
    private String graduateSchool;
    @ApiModelProperty("学历")
    private String education;
    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("用户id")
    private Long accUserId;
}
