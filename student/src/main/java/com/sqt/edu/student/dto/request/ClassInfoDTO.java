package com.sqt.edu.student.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-14 17:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ApiModel
public class ClassInfoDTO implements Serializable {

    @ApiModelProperty("补课班级信息id")
    private Long classInfoId;
    @ApiModelProperty("补课科目")
    @NotBlank
    private String subjects;
    @ApiModelProperty("年级")
    @NotBlank
    private String grade;
    @ApiModelProperty("补课天数")
    @NotNull
    private Integer days;
    @ApiModelProperty("年份")
    @NotNull
    private Integer year;
    @ApiModelProperty("计划招收学生数量")
    @NotNull
    private Integer planAmount;
    @ApiModelProperty("假期类型: 1:暑假,2:寒假")
    @NotNull
    private Integer vacationType;
}
