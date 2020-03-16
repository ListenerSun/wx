package com.sqt.edu.student.dto.request;

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
public class ClassOrderDTO implements Serializable {

    private static final long serialVersionUID = 3601367411344998646L;

    @ApiModelProperty("补课班级id")
    @NotNull
    private Long classInfoId;
    @ApiModelProperty("学生姓名")
    @NotBlank
    private String StudentName;
    @ApiModelProperty("年级")
    @NotBlank
    private String grade;
    @ApiModelProperty("手机号")
    @NotBlank
    private String phone;
}
