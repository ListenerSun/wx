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

    @NotBlank
    @ApiModelProperty("姓名")
    private String teacherName;
    @NotBlank
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
    @ApiModelProperty("手机号")
    private String phone;
    @NotNull
    @ApiModelProperty("用户id")
    private Long accUserId;
}
