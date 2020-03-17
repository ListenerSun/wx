package com.sqt.edu.student.dto.request;

import com.sqt.edu.core.validation.PhoneNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-13 23:37
 */
@ApiModel()
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuRegisterInfoDTO implements Serializable {

    private static final long serialVersionUID = 3601367411344998646L;

    @NotBlank(message = "学生姓名不能为空!")
    @ApiModelProperty("学生姓名")
    private String studentName;
    @NotBlank(message = "学生性别不能为空!")
    @ApiModelProperty("性别")
    private String sex;
    @PhoneNumber(message = "手机号格式错误!")
    @NotBlank(message = "联系方式别不能为空!")
    @ApiModelProperty("学生联系方式")
    private String phone;
    @ApiModelProperty("就读院校")
    private String school;
    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("地址")
    private String address;
    @NotNull(message = "补课班级信息Id不能为空!")
    @ApiModelProperty("补课班级信息Id")
    private Long classInfoId;
    @NotBlank(message = "补课科目不能为空")
    @ApiModelProperty("补课科目")
    private String subjects;
    @ApiModelProperty("用户id")
    private Long accUserId;
}
