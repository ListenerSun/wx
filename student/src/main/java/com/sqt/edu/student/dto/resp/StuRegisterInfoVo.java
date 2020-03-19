package com.sqt.edu.student.dto.resp;

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
 * @Date: Created in 2020-03-19 19:58
 */
@ApiModel("学生报名信息返回Vo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuRegisterInfoVo implements Serializable {
    @ApiModelProperty("学生姓名")
    private String studentName;
    @ApiModelProperty("学生联系方式")
    private String phone;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("补课班级信息Id")
    private Long classInfoId;
    @ApiModelProperty("补课班级名称")
    private String className;
    @ApiModelProperty("补课年级")
    private String grade;
    @ApiModelProperty("补课科目")
    private String subjects;
    @ApiModelProperty("年份")
    private Integer year;
}
