package com.sqt.edu.teacher.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-24 18:23
 */
@ApiModel
@Builder
@Data
public class TeacherInfoDTO implements Serializable {

    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty("姓名")
    private String teacherName;
    @NotBlank(message = "性别不能为空")
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("生日")
    private Date birthday;
    @ApiModelProperty("毕业院校")
    private String graduateSchool;
    @ApiModelProperty("学历")
    private String education;
    @NotBlank(message = "身份证号不能为空")
    @ApiModelProperty("身份证号")
    private String idCard;
    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty("手机号")
    private String phone;
    @NotNull
    @ApiModelProperty("用户id")
    private Long accUserId;
}
